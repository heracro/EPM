package hihi.content.common.contentList;

import hihi.adapters.MainResourceAbstractAdapter;
import hihi.application.config.GuiConfig;
import hihi.application.config.ModuleConfig;
import hihi.components.MainController;
import hihi.content.common.ContentLayoutController;
import hihi.content.common.dataModel.AbstractContent;
import hihi.content.common.dataModel.AbstractDto;
import hihi.event.ListViewRowClickHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
        addSelectedColumn(0.02);
        addUidColumn(0.04);
        loadContentToTable();
        attachRowClickHandlers();
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

    protected void addSelectedColumn(double width) {
        log.info("\033[96m ({})addSelectedColumn({}) \033[m", this.getClass().getSimpleName(), width);
        selectedColumn.setCellValueFactory(cellData -> cellData.getValue().selectedProperty());
        selectedColumn.setCellFactory(CheckBoxTableCell.forTableColumn(selectedColumn));
        selectedColumn.prefWidthProperty().bind(table.widthProperty().multiply(width));
    }

    protected void addUidColumn(double width) {
        log.info("\033[96m ({}) addUidColumn({}) \033[m", this.getClass().getSimpleName(), width);
        uidColumn.setCellValueFactory(cellData -> cellData.getValue().uidProperty().asObject());
        uidColumn.prefWidthProperty().bind(table.widthProperty().multiply(width));
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
        log.info("\033[96m ({}) setMainController({})\033[m", this.getClass().getSimpleName(), mainController);
        this.mainController = mainController;
    }

    protected abstract Content mapInstance(DTO dto);

    ModuleConfig getModuleConfig() {
        return GuiConfig.MODULES_CONFIG.get(contentName);
    }

}
