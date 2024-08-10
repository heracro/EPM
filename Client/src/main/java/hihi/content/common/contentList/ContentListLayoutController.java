package hihi.content.common.contentList;

import hihi.adapters.MainResourceAbstractAdapter;
import hihi.application.config.GuiConfig;
import hihi.components.MainController;
import hihi.content.common.ContentLayoutController;
import hihi.content.common.dataModel.AbstractContent;
import hihi.content.common.dataModel.AbstractDto;
import hihi.event.ListViewRowClickHandler;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.util.Callback;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ToString(callSuper = true)
public abstract class ContentListLayoutController<
        Content extends AbstractContent,
        DTO extends AbstractDto,
        Adapter extends MainResourceAbstractAdapter<DTO>>
        extends ContentLayoutController<Adapter> {

    @FXML
    protected TableView<Content> table;
    @FXML
    protected TableColumn<Content, Boolean> selectedColumn;
    @FXML
    protected TableColumn<Content, Integer> uidColumn;

    protected ContentListLayoutController(Adapter adapter, String contentName) {
        super(adapter, contentName);
        log.info("\033[96m ContentListLayoutController({}, {}) \033[m", adapter, contentName);
    }

    public void initialize() {
        log.info("\033[96m initialize() \033[m");
        super.initialize();
        loadContentToTable();
        attachRowClickHandlers();
        contentView.widthProperty().addListener((obs, oldWidth, newWidth) -> adjustColumnWidths(newWidth.doubleValue()));
        selectedColumn.setCellFactory(CheckBoxTableCell.forTableColumn(selectedColumn));
    }

    protected void loadContentToTable() {
        log.info("\033[96m ({}) loadContentList() \033[m",this.getClass().getSimpleName());
        try {
            List<DTO> dtos = adapter.getAll();
            ObservableList<Content> content = FXCollections.observableArrayList();
            for (DTO dto : dtos) {
                content.add(mapInstance(dto));
            }
            log.info("\033[96m Fetched {} Objects \033[m", content.size());
            table.setItems(content);
        } catch (Exception e) {
            log.error("\033[96m Error fetching contents list: {} \033[m", e.getMessage());
        }
    }

    /**
     * TODO: listeners only to rows with content.
     */
    protected void attachRowClickHandlers() {
        log.info("\033[96m ({}) attachRowClickHandlers() \033[m", this.getClass().getSimpleName());
        log.info("\033[96m Reference to MainController = {} \033[m", mainController == null ? "null" : mainController);
        table.setRowFactory(t -> {
            TableRow<Content> row = new TableRow<>();
            row.setOnMouseClicked(
                    new ListViewRowClickHandler<Content>(mainController, GuiConfig.getConfigForModule(contentName))
            );
            return row;
        });
    }

    public void setMainController(MainController mainController) {
        log.info("\033[96m ({}) setMainController({}) \033[m", this.getClass().getSimpleName(), mainController);
        this.mainController = mainController;
    }

    protected void setupColumns() {
        log.info("\033[96m setupColumns() \033[m");
        bindColumnsToProperties();
        adjustColumnWidths(GuiConfig.BASE_WIDTH - GuiConfig.LEFT_PANEL_WIDTH);
    }

    @SuppressWarnings("unchecked")
    protected void adjustColumnWidths(double contentViewWidth) {
        log.info("\033[96m adjustColumnWidths({}) \033[m", contentViewWidth);
        int i = 0;
        List<Field> fields = getEligibleColumnFields();
        List<Double> multipliers = getColumnWidthsMultipliers();
        for (Field field : fields) {
            if (i >= multipliers.size()) break;
            try {
                field.setAccessible(true);
                double multiplier = getColumnWidthsMultipliers().get(i++);
                double prefWidth = calculateColumnWidth(contentViewWidth, multiplier);
                log.info("\033[96m {}.setPrefWidth({}) [multiplier={}] \033[m", field.getName(), prefWidth, multiplier);
                ((TableColumn<Content, ?>) field.get(this)).setPrefWidth(prefWidth);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void bindColumnsToProperties() {
        log.info("\033[96m bindColumnsToProperties() \033[m");
        List<Field> fields = getEligibleColumnFields();
        for (Field field : fields) {
            String propertyName = field.getName().replace("Column", "Property");
            try {
                field.setAccessible(true);
                Method propertyMethod = getContentClass().getMethod(propertyName);
                log.info("\033[96m Binding column {} to {} \033[m", field.getName(), propertyName);
                TableColumn<Content, ?> column = (TableColumn<Content, ?>) field.get(this);
                column.setCellValueFactory(getColumnBindingCallback(propertyMethod));
            } catch (NoSuchMethodException | IllegalAccessException e) {
                log.error("\033[96m No such method or access issue: {}.{}() \033[m",
                        getContentClass().getSimpleName(), propertyName);
            } finally {
                field.setAccessible(false);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private <T> Callback<TableColumn.CellDataFeatures<Content, T>, ObservableValue<T>>
    getColumnBindingCallback(Method propertyMethod) {
        return (cellData -> {
            try {
                return (ObservableValue<T>) propertyMethod.invoke(cellData.getValue());
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private List<Field> getAllFields() {
        List<Field> fields = new ArrayList<>();
        Class<?> superclass = this.getClass().getSuperclass();
        if (superclass != null) {
            fields.addAll(Arrays.asList(superclass.getDeclaredFields()));
        }
        fields.addAll(Arrays.asList(this.getClass().getDeclaredFields()));
        return fields;
    }

    private List<Field> getEligibleColumnFields() {
        return getAllFields().stream()
                .filter(field -> field.getName().endsWith("Column"))
                .filter(field -> TableColumn.class.isAssignableFrom(field.getType()))
                .collect(Collectors.toList());
    }

    protected double calculateColumnWidth(double contentViewWidth, double widthMultiplier) {
        log.info("\033[96m calculateColumnWidth({}, {}) \033[m", contentViewWidth, widthMultiplier);
        if (contentViewWidth < GuiConfig.MIN_DYNAMIC_CONTENT_WIDTH) {
            return GuiConfig.MIN_DYNAMIC_CONTENT_WIDTH * widthMultiplier;
        } else if (contentViewWidth <= GuiConfig.MAX_DYNAMIC_CONTENT_WIDTH) {
            return contentViewWidth * widthMultiplier;
        }
        return GuiConfig.MAX_DYNAMIC_CONTENT_WIDTH * widthMultiplier;
    }

    protected List<Double> getColumnWidthsMultipliers() {
        return Arrays.stream(GuiConfig.BASE_COL_WIDTH)
                .boxed()
                .collect(Collectors.toList());
    }

    protected abstract Content mapInstance(DTO dto);

    protected abstract Class<Content> getContentClass();

}
