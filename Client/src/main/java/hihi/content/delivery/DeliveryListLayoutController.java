package hihi.content.delivery;

import hihi.adapters.DeliveryAdapter;
import hihi.content.common.contentList.ContentListLayoutController;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

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

    public DeliveryListLayoutController() {
        super(new DeliveryAdapter(), "Delivery");
        log.info("\033[93mDeliveryListLayoutController()\033[0m");
    }

    @FXML
    @Override
    public void initialize() {
        log.info("\033[93m initialize() \033[0m");
        super.initialize();
        addMaterialColumn(DELIVERY_LIST_COL_WIDTH[2]);
        addQtyColumn(DELIVERY_LIST_COL_WIDTH[3]);
        addUnitColumn(DELIVERY_LIST_COL_WIDTH[4]);
        addUnitPriceColumn(DELIVERY_LIST_COL_WIDTH[5]);
        addTotalPriceColumn(DELIVERY_LIST_COL_WIDTH[6]);
        addPlannedDateColumn(DELIVERY_LIST_COL_WIDTH[7]);
    }

    private void addMaterialColumn(double width) {
        log.info("\033[93m addMaterialColumn({}) \033[0m", width);
        materialColumn.setCellValueFactory(cellData -> cellData.getValue().materialNameProperty());
        materialColumn.prefWidthProperty().bind(table.widthProperty().multiply(width));
    }

    private void addQtyColumn(double width) {
        log.info("\033[93m addQtyColumn({}) \033[0m", width);
        qtyColumn.setCellValueFactory(cellData -> cellData.getValue().qtyProperty().asObject());
        qtyColumn.prefWidthProperty().bind(table.widthProperty().multiply(width));
    }

    private void addUnitColumn(double width) {
        log.info("\033[93m addUnitColumn({}) \033[0m", width);
        unitColumn.setCellValueFactory(cellData -> cellData.getValue().unitProperty());
        unitColumn.prefWidthProperty().bind(table.widthProperty().multiply(width));
    }

    private void addUnitPriceColumn(double width) {
        log.info("\033[93m addUnitPriceColumn({}) \033[0m", width);
        unitPriceColumn.setCellValueFactory(cellData -> cellData.getValue().unitPriceProperty().asObject());
        unitPriceColumn.prefWidthProperty().bind(table.widthProperty().multiply(width));
    }

    private void addTotalPriceColumn(double width) {
        log.info("\033[93m addTotalPriceColumn({}) \033[0m", width);
        totalPriceColumn.setCellValueFactory(cellData -> cellData.getValue().totalPriceProperty().asObject());
        totalPriceColumn.prefWidthProperty().bind(table.widthProperty().multiply(width));
    }

    private void addPlannedDateColumn(double width) {
        log.info("\033[93m addPlannedDateColumn({}) \033[0m", width);
        plannedDateColumn.setCellValueFactory(cellData -> cellData.getValue().plannedDateProperty());
        plannedDateColumn.prefWidthProperty().bind(table.widthProperty().multiply(width));
    }

}
