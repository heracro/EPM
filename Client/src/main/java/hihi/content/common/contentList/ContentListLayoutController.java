package hihi.content.common.contentList;

import hihi.application.config.GuiConfig;
import hihi.application.config.ModuleConfig;
import hihi.components.MainController;
import hihi.content.common.ContentLayoutController;
import hihi.content.common.dataModel.AbstractContent;
import hihi.event.ListViewRowClickHandler;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ToString(callSuper = true)
public abstract class ContentListLayoutController<
        Content extends AbstractContent>
        extends ContentLayoutController {

    @FXML
    protected TableView<Content> table;
    @FXML
    protected TableColumn<Content, Boolean> selectedColumn;
    @FXML
    protected TableColumn<Content, Integer> uidColumn;

    protected ContentListLayoutController(String contentName) {
        super(contentName);
        log.info("\033[96m ContentListLayoutController({}) \033[m", contentName);
    }

    public void initialize() {
        log.info("\033[96m initialize() \033[m");
        super.initialize();
        loadContentToTable();
        attachRowClickHandlers();
        contentView.widthProperty().addListener((obs, oldWidth, newWidth) -> adjustColumnWidths(newWidth.doubleValue()));
        selectedColumn.setCellFactory(CheckBoxTableCell.forTableColumn(selectedColumn));
        VBox.setVgrow(table, Priority.ALWAYS);
        log.info("\033[96m END OF initialize() \033[m");
    }

    public void loadContentToTable() {
        log.info("\033[96m ({}) loadContentList() \033[m",this.getClass().getSimpleName());
        List<Content> contentList = getAdapter().getAll();
        if (contentList == null) return;
        try {
            ObservableList<Content> content = FXCollections.observableArrayList();
            content.addAll(contentList);
            table.setItems(content);
        } catch (Exception e) {
            log.error("\033[96m Error fetching contents list: {} \033[m", e.getMessage());
            log.error(Arrays.toString(e.getStackTrace()));
        }
    }

    protected void attachRowClickHandlers() {
        log.info("\033[96m ({}) attachRowClickHandlers() \033[m", this.getClass().getSimpleName());
        table.setRowFactory(t -> {
            TableRow<Content> row = new TableRow<>();
            row.setOnMouseClicked(
                    new ListViewRowClickHandler<Content>(mainController, moduleName)
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
        int i = 0;
        List<Field> fields = getEligibleColumnFields();
        List<Double> multipliers = getColumnWidthsMultipliers();
        for (Field field : fields) {
            if (i >= multipliers.size()) break;
            try {
                field.setAccessible(true);
                double multiplier = getColumnWidthsMultipliers().get(i++);
                double prefWidth = calculateColumnWidth(contentViewWidth, multiplier);
                ((TableColumn<Content, ?>) field.get(this)).setPrefWidth(prefWidth);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private double calculateColumnWidth(double contentViewWidth, double widthMultiplier) {
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

    @SuppressWarnings("unchecked")
    private void bindColumnsToProperties() {
        log.info("\033[96m bindColumnsToProperties() \033[m");
        List<Field> fields = getEligibleColumnFields();
        for (Field field : fields) {
            String propertyName = field.getName().replace("Column", "Property");
            try {
                field.setAccessible(true);
                Method propertyMethod = ModuleConfig.getInstance(moduleName).getContentClass().getMethod(propertyName);
                //log.info("\033[96m Binding column {} to {} \033[m", field.getName(), propertyName);
                TableColumn<Content, ?> column = (TableColumn<Content, ?>) field.get(this);
                column.setCellValueFactory(getColumnBindingCallback(propertyMethod));
            } catch (NoSuchMethodException | IllegalAccessException e) {
                log.error("\033[96m No such method or access issue: {}.{}() \033[m",
                        moduleName, propertyName);
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

    protected List<Field> getEligibleColumnFields() {
        return getAllFields().stream()
                .filter(field -> field.getName().endsWith("Column"))
                .filter(field -> TableColumn.class.isAssignableFrom(field.getType()))
                .collect(Collectors.toList());
    }

}
