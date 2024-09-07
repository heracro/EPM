package hihi.content.bom;

import hihi.application.config.GuiConfig;
import hihi.content.common.contentList.ContentListLayoutController;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.BorderPane;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@Component
public class BomListLayoutController
        extends ContentListLayoutController<Bom> {

    @FXML
    private BorderPane bomList;
    @FXML
    private TableColumn<Bom, Integer> materialIdColumn;
    @FXML
    private TableColumn<Bom, String> materialNameColumn;
    @FXML
    private TableColumn<Bom, Float> qtyColumn;

    protected BomListLayoutController() {
        super("Bom");
    }

    @Override
    protected List<Double> getColumnWidthsMultipliers() {
        return Stream.concat(super.getColumnWidthsMultipliers().stream(),
                Arrays.stream(GuiConfig.BOM_LIST_COL_WIDTH).boxed()).toList();
    }

    @FXML
    @Override
    public void initialize() {
        log.info("\033[93m initialize() \033[0m");
        super.initialize();
        setupColumns();
    }


}
