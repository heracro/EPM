package hihi.event;

import hihi.application.config.ModuleConfig;
import hihi.content.common.dataModel.AbstractContent;
import hihi.components.MainController;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import lombok.extern.slf4j.Slf4j;
import java.util.Map;

@Slf4j
public class ListViewRowClickHandler<Content extends AbstractContent> implements EventHandler<MouseEvent> {

    private final MainController mainController;
    private final Map.Entry<String, ModuleConfig> moduleConfig;

    public ListViewRowClickHandler(MainController mainController, Map.Entry<String, ModuleConfig> moduleConfig) {
        this.moduleConfig = moduleConfig;
        this.mainController = mainController;
        if (mainController == null) log.info("\033[91m Reference to MainController = NULL !");
    }

    @Override
    public void handle(MouseEvent event) {
        if (event.getClickCount() == 1) handleSingleClick(event);
        else if (event.getClickCount() > 1) handleDoubleClick(event);
    }

    private void handleSingleClick(MouseEvent event) {
        TableRow<Content> tableRow = getClickedRow(event);
        if (tableRow == null || tableRow.isEmpty()) return;
        TableView<Content> tableView = tableRow.getTableView();
        if (getSelectedColumn(tableView) != null) {
            tableRow.getItem().toggleSelected();
            tableView.refresh();
        }
    }

    private void handleDoubleClick(MouseEvent event) {
        log.info("\033[96m Mouse Event passed for handling: {}\033[0m", event);
        TableRow<Content> tableRow = getClickedRow(event);
        if (tableRow != null && !tableRow.isEmpty() && event.getClickCount() > 1) {
            Content clickedItem = tableRow.getItem();
            mainController.setContentObjectView(moduleConfig, clickedItem);
        }
    }

    @SuppressWarnings("unchecked")
    private TableRow<Content> getClickedRow(MouseEvent event) {
        Object source = event.getSource();
        if (source instanceof TableRow) {
            return (TableRow<Content>) source;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private TableColumn<Content, Boolean> getSelectedColumn(TableView<Content> tableView) {
        for (TableColumn<Content, ?> column : tableView.getColumns()) {
            if (column.getId().equals("selectedColumn")) {
                return (TableColumn<Content, Boolean>) column;
            }
        }
        return null;
    }

}
