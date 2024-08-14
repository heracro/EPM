package hihi.content.delivery;

import hihi.content.common.dataModel.AbstractContent;
import javafx.beans.property.*;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

@Slf4j
@ToString(callSuper = true)
@NoArgsConstructor
public class Delivery extends AbstractContent {

    private final IntegerProperty materialId = new SimpleIntegerProperty();
    private final StringProperty materialName = new SimpleStringProperty();
    private final StringProperty store  = new SimpleStringProperty();
    private final StringProperty invoiceNumber = new SimpleStringProperty();
    private final ObjectProperty<LocalDate> orderDate = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDate> plannedDate = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDate> deliveryDate = new SimpleObjectProperty<>();
    private final FloatProperty qty = new SimpleFloatProperty();
    private final StringProperty unit = new SimpleStringProperty();
    private final FloatProperty unitPrice = new SimpleFloatProperty();
    private final FloatProperty totalPrice = new SimpleFloatProperty();

    public Delivery(DeliveryDto dto) {
        uid.set(dto.getUid());
        materialId.set(dto.getMaterial().getUid());
        materialName.set(dto.getMaterial().getName());
        if (dto.getInvoice() != null) {
            store.set(dto.getInvoice().getShop().getName());
            invoiceNumber.set(dto.getInvoice().getInvoiceNumber());
            orderDate.set(dto.getInvoice().getOrderDate());
            plannedDate.set(dto.getInvoice().getPlannedDate());
            deliveryDate.set(dto.getInvoice().getDeliveryDate());
        }
        qty.set(dto.getQty());
        unit.set(dto.getUnit().toValue());
        unitPrice.set(dto.getUnitPrice());
        totalPrice.set(dto.getTotalPrice());
        log.info("\033[95m Constructed Delivery: {} \033[m", this);
    }

    public IntegerProperty materialIdProperty() { return materialId; }
    public StringProperty materialNameProperty() { return materialName; }
    public StringProperty storeProperty() { return store; }
    public StringProperty invoiceNumberProperty() { return invoiceNumber; }
    public ObjectProperty<LocalDate> orderDateProperty() { return orderDate; }
    public ObjectProperty<LocalDate> plannedDateProperty() { return plannedDate; }
    public ObjectProperty<LocalDate> deliveryDateProperty() { return deliveryDate; }
    public FloatProperty qtyProperty() { return qty; }
    public StringProperty unitProperty() { return unit; }
    public FloatProperty unitPriceProperty() { return unitPrice; }
    public FloatProperty totalPriceProperty() { return totalPrice; }

}
