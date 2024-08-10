package hihi.content.shop;

import hihi.adapters.ShopAdapter;
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
public class ShopListLayoutController
        extends ContentListLayoutController<Shop, ShopDto, ShopAdapter> {

    @FXML
    private TableColumn<Shop, String> nameColumn;
    @FXML
    private TableColumn<Shop, String> websiteColumn;
    @FXML
    private TableColumn<Shop, String> addressColumn;
    @FXML
    private TableColumn<Shop, String> emailColumn;
    @FXML
    private TableColumn<Shop, String> phoneColumn;
    @FXML
    private TableColumn<Shop, String> memoColumn;

    @Override
    protected Shop mapInstance(ShopDto dto) { return new Shop(dto); }

    @Override
    protected Class<Shop> getContentClass() {
        return Shop.class;
    }

    @Override
    protected List<Double> getColumnWidthsMultipliers() {
        return Stream.concat(super.getColumnWidthsMultipliers().stream(),
                Arrays.stream(GuiConfig.SHOP_LIST_COL_WIDTH).boxed()).toList();
    }

    public ShopListLayoutController() {
        super(new ShopAdapter(), "Shop");
        log.info("\033[93m ShopListLayoutController() \033[0m");
    }

    @FXML
    @Override
    public void initialize() {
        log.info("\033[93m initialize() \033[0m");
        super.initialize();
        setupColumns();
    }

}
