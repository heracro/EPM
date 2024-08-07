package hihi.content.common;

import hihi.adapters.MainResourceAbstractAdapter;
import hihi.gui.components.ContentWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public abstract class ContentListLayout<
        Content extends AbstractContent,
        DTO extends AbstractDto,
        Adapter extends MainResourceAbstractAdapter<DTO>>
        extends ContentWindow {

    protected abstract TableView<Content> getTable();

    protected abstract Adapter getAdapter();

    protected abstract Content mapInstance(DTO dto);

    protected void loadContentList() {
        try {
            List<DTO> dtos = getAdapter().getAll();
            ObservableList<Content> content = FXCollections.observableArrayList();
            for (DTO dto : dtos) {
                content.add(mapInstance(dto));
            }
            log.info("\033[32mFetched {} Objects\033[m", content.size());
            getTable().setItems(content);
        } catch (Exception e) {
            log.error("Error fetching contents list: {}", e.getMessage());
        }
    }

    protected void addSelectedColumn(double width) {
        TableColumn<Content, Boolean> selectedColumn = new TableColumn<>("Selected");
        selectedColumn.setCellValueFactory(cellData -> cellData.getValue().getSelected());
        selectedColumn.setCellFactory(CheckBoxTableCell.forTableColumn(selectedColumn));
        selectedColumn.prefWidthProperty().bind(getTable().widthProperty().multiply(width));
        getTable().getColumns().add(selectedColumn);
    }

    protected void addUidColumn(double width) {
        TableColumn<Content, String> uidColumn = new TableColumn<>("UID");
        uidColumn.setCellValueFactory(cellData -> cellData.getValue().getUid().asString());
        uidColumn.prefWidthProperty().bind(getTable().widthProperty().multiply(width));
        getTable().getColumns().add(uidColumn);
    }

}
