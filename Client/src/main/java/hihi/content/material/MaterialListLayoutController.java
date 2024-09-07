package hihi.content.material;

import hihi.application.config.GuiConfig;
import hihi.content.common.contentList.ContentListLayoutController;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@Component
public class MaterialListLayoutController
        extends ContentListLayoutController<Material> {

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
    protected List<Double> getColumnWidthsMultipliers() {
        return Stream.concat(super.getColumnWidthsMultipliers().stream(),
                Arrays.stream(GuiConfig.MATERIAL_LIST_COL_WIDTH).boxed()).toList();
    }

    public MaterialListLayoutController() {
        super("Material");
        log.info("\033[93mMaterialListLayoutController()\033[0m");
    }

    @FXML
    @Override
    public void initialize() {
        log.info("\033[93m initialize() \033[0m");
        super.initialize();
        setupColumns();
    }

}
