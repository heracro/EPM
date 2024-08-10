package hihi.content.material;

import hihi.adapters.MaterialAdapter;
import static hihi.application.config.GuiConfig.MATERIAL_LIST_COL_WIDTH;

import hihi.content.common.contentList.ContentListLayoutController;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MaterialListLayoutController extends ContentListLayoutController<Material, MaterialDto, MaterialAdapter> {

    @FXML
    private TableColumn<Material, String> nameColumn;
    @FXML
    private TableColumn<Material, String> normColumn;
    @FXML
    private TableColumn<Material, String> dimensionsColumn;
    @FXML
    private TableColumn<Material, String> unitColumn;
    @FXML
    private TableColumn<Material, Float> weightColumn;
    @FXML
    private TableColumn<Material, Integer> totalQtyColumn;
    @FXML
    private TableColumn<Material, Integer> freeQtyColumn;
    @FXML
    private TableColumn<Material, String> datasheetColumn;


    @Override
    protected Material mapInstance(MaterialDto dto) {
        return new Material(dto);
    }

    public MaterialListLayoutController() {
        super(new MaterialAdapter(), "Material");
        log.info("\033[93mMaterialListLayoutController()\033[0m");
    }

    @FXML
    @Override
    public void initialize() {
        log.info("\033[93m initialize() \033[0m");
        addNameColumn(MATERIAL_LIST_COL_WIDTH[2]);
        addNormColumn(MATERIAL_LIST_COL_WIDTH[3]);
        addDimensionColumn(MATERIAL_LIST_COL_WIDTH[4]);
        addUnitColumn(MATERIAL_LIST_COL_WIDTH[5]);
        addWeightColumn(MATERIAL_LIST_COL_WIDTH[6]);
        addTotalQtyColumn(MATERIAL_LIST_COL_WIDTH[7]);
        addFreeQtyColumn(MATERIAL_LIST_COL_WIDTH[8]);
        addDatasheetColumn(MATERIAL_LIST_COL_WIDTH[9]);
        super.initialize();
    }


    private void addNameColumn(double width) {
        log.info("\033[93m addNameColumn({}) \033[0m", width);
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        nameColumn.prefWidthProperty().bind(table.widthProperty().multiply(width));
    }

    private void addNormColumn(double width) {
        log.info("\033[93m addNormColumn({}) \033[0m", width);
        normColumn.setCellValueFactory(cellData -> cellData.getValue().normProperty());
        normColumn.prefWidthProperty().bind(table.widthProperty().multiply(width));
    }

    private void addDimensionColumn(double width) {
        log.info("\033[93m addDimensionColumn({}) \033[0m", width);
        dimensionsColumn.setCellValueFactory(cellData -> cellData.getValue().dimensionsProperty());
        dimensionsColumn.prefWidthProperty().bind(table.widthProperty().multiply(width));
    }

    private void addUnitColumn(double width) {
        log.info("\033[93m addUnitColumn({}) \033[0m", width);
        unitColumn.setCellValueFactory(cellData -> cellData.getValue().unitProperty());
        unitColumn.prefWidthProperty().bind(table.widthProperty().multiply(width));
    }

    private void addWeightColumn(double width) {
        log.info("\033[93m addWeightColumn({}) \033[0m", width);
        weightColumn.setCellValueFactory(cellData -> cellData.getValue().weightProperty().asObject());
        weightColumn.prefWidthProperty().bind(table.widthProperty().multiply(width));
    }

    private void addTotalQtyColumn(double width) {
        log.info("\033[93m addTotalQtyColumn({}) \033[0m", width);
        totalQtyColumn.setCellValueFactory(cellData -> cellData.getValue().totalQtyProperty().asObject());
        totalQtyColumn.prefWidthProperty().bind(table.widthProperty().multiply(width));
    }

    private void addFreeQtyColumn(double width) {
        log.info("\033[93m addFreeQtyColumn({}) \033[0m", width);
        freeQtyColumn.setCellValueFactory(cellData -> cellData.getValue().freeQtyProperty().asObject());
        freeQtyColumn.prefWidthProperty().bind(table.widthProperty().multiply(width));
    }

    private void addDatasheetColumn(double width) {
        log.info("\033[93m addDatasheetColumn({}) \033[0m", width);
        datasheetColumn.setCellValueFactory(cellData -> cellData.getValue().datasheetProperty());
        datasheetColumn.prefWidthProperty().bind(table.widthProperty().multiply(width));
    }

}
