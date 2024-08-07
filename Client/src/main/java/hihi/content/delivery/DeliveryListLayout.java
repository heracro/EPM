package hihi.content.delivery;

import hihi.adapters.DeliveryAdapter;
import hihi.content.common.ContentListLayout;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

import static hihi.application.config.GuiConfig.DELIVERY_LIST_COL_WIDTH;

@Slf4j
public class DeliveryListLayout extends ContentListLayout<Delivery, DeliveryDto, DeliveryAdapter> {

    @Getter(AccessLevel.PROTECTED)
    private TableView<Delivery> table;

    @Getter(AccessLevel.PROTECTED)
    private final DeliveryAdapter adapter = new DeliveryAdapter();

    @Override
    protected Delivery mapInstance(DeliveryDto dto) {
        return null;
    }

    public DeliveryListLayout() {
        this.setPadding(new Insets(2));
        table = new TableView<>();
        addSelectedColumn(DELIVERY_LIST_COL_WIDTH[0]);
        addUidColumn(DELIVERY_LIST_COL_WIDTH[1]);
        addMaterialColumn(DELIVERY_LIST_COL_WIDTH[2]);
        addQtyColumn(DELIVERY_LIST_COL_WIDTH[3]);
        addUnitColumn(DELIVERY_LIST_COL_WIDTH[4]);
        addUnitPriceColumn(DELIVERY_LIST_COL_WIDTH[5]);
        addTotalPriceColumn(DELIVERY_LIST_COL_WIDTH[6]);
        addPlannedDateColumn(DELIVERY_LIST_COL_WIDTH[7]);

        this.setCenter(table);
        loadContentList();
    }

//    private void addUidColumn(double width) {
//        TableColumn<Delivery, Integer> uidColumn = new TableColumn<>("UID");
//        uidColumn.setCellValueFactory(cellData -> cellData.getValue().getUid().asObject());
//        uidColumn.prefWidthProperty().bind(table.widthProperty().multiply(width));
//        table.getColumns().add(uidColumn);
//    }

    private void addMaterialColumn(double width) {
        TableColumn<Delivery, String> materialColumn = new TableColumn<>("Material");
        materialColumn.setCellValueFactory(cellData -> cellData.getValue().getMaterialName());
        materialColumn.prefWidthProperty().bind(table.widthProperty().multiply(width));
        table.getColumns().add(materialColumn);
    }

    private void addQtyColumn(double width) {
        TableColumn<Delivery, Float> qtyColumn = new TableColumn<>("Quantity");
        qtyColumn.setCellValueFactory(cellData -> cellData.getValue().getQty().asObject());
        qtyColumn.prefWidthProperty().bind(table.widthProperty().multiply(width));
        table.getColumns().add(qtyColumn);
    }

    private void addUnitColumn(double width) {
        TableColumn<Delivery, String> unitColumn = new TableColumn<>("Unit");
        unitColumn.setCellValueFactory(cellData -> cellData.getValue().getUnit());
        unitColumn.prefWidthProperty().bind(table.widthProperty().multiply(width));
        table.getColumns().add(unitColumn);
    }

    private void addUnitPriceColumn(double width) {
        TableColumn<Delivery, Float> unitPriceColumn = new TableColumn<>("Unit Price");
        unitPriceColumn.setCellValueFactory(cellData -> cellData.getValue().getUnitPrice().asObject());
        unitPriceColumn.prefWidthProperty().bind(table.widthProperty().multiply(width));
        table.getColumns().add(unitPriceColumn);
    }

    private void addTotalPriceColumn(double width) {
        TableColumn<Delivery, Float> totalPriceColumn = new TableColumn<>("Total Price");
        totalPriceColumn.setCellValueFactory(cellData -> cellData.getValue().getTotalPrice().asObject());
        totalPriceColumn.prefWidthProperty().bind(table.widthProperty().multiply(width));
        table.getColumns().add(totalPriceColumn);
    }

    private void addPlannedDateColumn(double width) {
        TableColumn<Delivery, LocalDate> plannedDateColumn = new TableColumn<>("Planned Delivery");
        plannedDateColumn.setCellValueFactory(cellData -> cellData.getValue().getPlannedDate());
        plannedDateColumn.prefWidthProperty().bind(table.widthProperty().multiply(width));
        table.getColumns().add(plannedDateColumn);
    }

}
