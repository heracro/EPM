package hihi.content.delivery;

import hihi.adapters.DeliveryAdapter;
import hihi.application.config.GuiConfig;
import hihi.content.common.contentList.ContentListLayoutController;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static hihi.application.config.GuiConfig.DELIVERY_LIST_COL_WIDTH;

@Slf4j
@Component
public class DeliveryListLayoutController
        extends ContentListLayoutController<Delivery, DeliveryDto, DeliveryAdapter> {

    @FXML
    private TableColumn<Delivery, String> materialColumn;
    @FXML
    private TableColumn<Delivery, Float> qtyColumn;
    @FXML
    private TableColumn<Delivery, String> unitColumn;
    @FXML
    private TableColumn<Delivery, Float> unitPriceColumn;
    @FXML
    private TableColumn<Delivery, Float> totalPriceColumn;
    @FXML
    private TableColumn<Delivery, LocalDate> plannedDateColumn;

    @Override
    protected Delivery mapInstance(DeliveryDto dto) {
        return new Delivery(dto);
    }

    @Override
    protected Class<Delivery> getContentClass() {
        return Delivery.class;
    }

    @Override
    protected List<Double> getColumnWidthsMultipliers() {
        return Stream.concat(super.getColumnWidthsMultipliers().stream(),
                        Arrays.stream(GuiConfig.DELIVERY_LIST_COL_WIDTH).boxed()).toList();
    }

    public DeliveryListLayoutController() {
        super(new DeliveryAdapter(), "Delivery");
        log.info("\033[93mDeliveryListLayoutController()\033[0m");
    }

    @FXML
    @Override
    public void initialize() {
        log.info("\033[93m initialize() \033[0m");
        super.initialize();
        setupColumns();
    }

}
