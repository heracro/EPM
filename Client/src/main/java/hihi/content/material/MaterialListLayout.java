package hihi.content.material;

import hihi.adapters.MaterialAdapter;
import hihi.application.config.GuiConfig;
import hihi.content.common.ContentListLayout;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MaterialListLayout extends ContentListLayout<Material, MaterialDto, MaterialAdapter> {

    @Getter(AccessLevel.PROTECTED)
    private TableView<Material> table;

    @Getter(AccessLevel.PROTECTED)
    private final MaterialAdapter adapter = new MaterialAdapter();

    @Override
    protected Material mapInstance(MaterialDto dto) {
        return new Material(dto);
    }

    public MaterialListLayout() {
        this.setPadding(new Insets(2));
        table = new TableView<>();


        TableColumn<Material, Integer> uidColumn = new TableColumn<>("UID");
        uidColumn.setCellValueFactory(cellData -> cellData.getValue().getUid().asObject());
        uidColumn.prefWidthProperty()
                .bind(table.widthProperty().multiply(GuiConfig.MATERIAL_LIST_COL_WIDTH[1]));

        TableColumn<Material, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
        nameColumn.prefWidthProperty()
                .bind(table.widthProperty().multiply(GuiConfig.MATERIAL_LIST_COL_WIDTH[2]));

        TableColumn<Material, String> normColumn = new TableColumn<>("Norm");
        normColumn.setCellValueFactory(cellData -> cellData.getValue().getNorm());
        normColumn.prefWidthProperty()
                .bind(table.widthProperty().multiply(GuiConfig.MATERIAL_LIST_COL_WIDTH[3]));

        TableColumn<Material, String> dimensionsColumn = new TableColumn<>("Dimensions");
        dimensionsColumn.setCellValueFactory(cellData -> cellData.getValue().getDimensions());
        dimensionsColumn.prefWidthProperty()
                .bind(table.widthProperty().multiply(GuiConfig.MATERIAL_LIST_COL_WIDTH[4]));

        TableColumn<Material, String> unitColumn = new TableColumn<>("Unit");
        unitColumn.setCellValueFactory(cellData -> cellData.getValue().getUnit());
        unitColumn.prefWidthProperty()
                .bind(table.widthProperty().multiply(GuiConfig.MATERIAL_LIST_COL_WIDTH[5]));

        TableColumn<Material, Float> weightColumn = new TableColumn<>("Weight");
        weightColumn.setCellValueFactory(cellData -> cellData.getValue().getWeight().asObject());
        weightColumn.prefWidthProperty()
                .bind(table.widthProperty().multiply(GuiConfig.MATERIAL_LIST_COL_WIDTH[6]));

        TableColumn<Material, Integer> totalQtyColumn = new TableColumn<>("Total Qty");
        totalQtyColumn.setCellValueFactory(cellData -> cellData.getValue().getTotalQty().asObject());
        totalQtyColumn.prefWidthProperty()
                .bind(table.widthProperty().multiply(GuiConfig.MATERIAL_LIST_COL_WIDTH[7]));

        TableColumn<Material, Integer> freeQtyColumn = new TableColumn<>("Free Qty");
        freeQtyColumn.setCellValueFactory(cellData -> cellData.getValue().getFreeQty().asObject());
        freeQtyColumn.prefWidthProperty()
                .bind(table.widthProperty().multiply(GuiConfig.MATERIAL_LIST_COL_WIDTH[8]));

        TableColumn<Material, String> datasheetColumn = new TableColumn<>("Datasheet");
        datasheetColumn.setCellValueFactory(cellData -> cellData.getValue().getDatasheet());
        datasheetColumn.prefWidthProperty()
                .bind(table.widthProperty().multiply(GuiConfig.MATERIAL_LIST_COL_WIDTH[9]));

        table.getColumns().addAll(
                uidColumn, nameColumn, normColumn, dimensionsColumn, unitColumn,
                weightColumn, totalQtyColumn, freeQtyColumn, datasheetColumn
        );

        this.setCenter(table);
        loadContentList();
    }

}
