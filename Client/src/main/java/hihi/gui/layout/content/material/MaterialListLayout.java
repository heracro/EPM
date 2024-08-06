package hihi.gui.layout.content.material;

import hihi.adapters.MaterialAdapter;
import hihi.application.config.GuiConfig;
import hihi.dto.MaterialDto;
import hihi.gui.components.ContentWindow;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class MaterialListLayout extends ContentWindow {

    private TableView<Material> materialTable;

    public MaterialListLayout() {
        this.setPadding(new Insets(2));
        materialTable = new TableView<>();

        TableColumn<Material, Integer> uidColumn = new TableColumn<>("UID");
        uidColumn.setCellValueFactory(cellData -> cellData.getValue().getUid().asObject());
        uidColumn.prefWidthProperty()
                .bind(materialTable.widthProperty().multiply(GuiConfig.MATERIAL_LIST_COL_WIDTH[0]));

        TableColumn<Material, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
        nameColumn.prefWidthProperty()
                .bind(materialTable.widthProperty().multiply(GuiConfig.MATERIAL_LIST_COL_WIDTH[1]));

        TableColumn<Material, String> normColumn = new TableColumn<>("Norm");
        normColumn.setCellValueFactory(cellData -> cellData.getValue().getNorm());
        normColumn.prefWidthProperty()
                .bind(materialTable.widthProperty().multiply(GuiConfig.MATERIAL_LIST_COL_WIDTH[2]));

        TableColumn<Material, String> dimensionsColumn = new TableColumn<>("Dimensions");
        dimensionsColumn.setCellValueFactory(cellData -> cellData.getValue().getDimensions());
        dimensionsColumn.prefWidthProperty()
                .bind(materialTable.widthProperty().multiply(GuiConfig.MATERIAL_LIST_COL_WIDTH[3]));

        TableColumn<Material, String> unitColumn = new TableColumn<>("Unit");
        unitColumn.setCellValueFactory(cellData -> cellData.getValue().getUnit());
        unitColumn.prefWidthProperty()
                .bind(materialTable.widthProperty().multiply(GuiConfig.MATERIAL_LIST_COL_WIDTH[4]));

        TableColumn<Material, Float> weightColumn = new TableColumn<>("Weight");
        weightColumn.setCellValueFactory(cellData -> cellData.getValue().getWeight().asObject());
        weightColumn.prefWidthProperty()
                .bind(materialTable.widthProperty().multiply(GuiConfig.MATERIAL_LIST_COL_WIDTH[5]));

        TableColumn<Material, Integer> totalQtyColumn = new TableColumn<>("Total Qty");
        totalQtyColumn.setCellValueFactory(cellData -> cellData.getValue().getTotalQty().asObject());
        totalQtyColumn.prefWidthProperty()
                .bind(materialTable.widthProperty().multiply(GuiConfig.MATERIAL_LIST_COL_WIDTH[6]));

        TableColumn<Material, Integer> freeQtyColumn = new TableColumn<>("Free Qty");
        freeQtyColumn.setCellValueFactory(cellData -> cellData.getValue().getFreeQty().asObject());
        freeQtyColumn.prefWidthProperty()
                .bind(materialTable.widthProperty().multiply(GuiConfig.MATERIAL_LIST_COL_WIDTH[7]));

        TableColumn<Material, String> datasheetColumn = new TableColumn<>("Datasheet");
        datasheetColumn.setCellValueFactory(cellData -> cellData.getValue().getDatasheet());
        datasheetColumn.prefWidthProperty()
                .bind(materialTable.widthProperty().multiply(GuiConfig.MATERIAL_LIST_COL_WIDTH[8]));

        materialTable.getColumns().addAll(
                uidColumn, nameColumn, normColumn, dimensionsColumn, unitColumn,
                weightColumn, totalQtyColumn, freeQtyColumn, datasheetColumn
        );

        this.setCenter(materialTable);
        loadMaterials();
    }

    private void loadMaterials() {
        try {
            MaterialAdapter adapter = new MaterialAdapter();
            List<MaterialDto> materialDtos = adapter.getMaterials();
            ObservableList<Material> materials = FXCollections.observableArrayList();
            for (MaterialDto materialDto : materialDtos) {
                materials.add(new Material(materialDto));
            }
            log.info("\033[32mFetched {} Materials\033[m", materials.size());
            materialTable.setItems(materials);
        } catch (Exception e) {
            log.error(e.toString());
        }
    }

}
