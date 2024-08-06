package hihi.gui.layout.content.delivery;

import hihi.dto.DeliveryDto;
import javafx.beans.property.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class Delivery {

    private final BooleanProperty selected = new SimpleBooleanProperty(false);
    private final IntegerProperty uid = new SimpleIntegerProperty();
    private final IntegerProperty materialId = new SimpleIntegerProperty();
    private final StringProperty materialName = new SimpleStringProperty();
    private final StringProperty store  = new SimpleStringProperty();
    private final StringProperty invoiceNumber = new SimpleStringProperty();
    private final ObjectProperty<LocalDate> orderDate = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDate> plannedDate = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDate> deliveryDate = new SimpleObjectProperty<>();
    private final IntegerProperty qty = new SimpleIntegerProperty();
    private final StringProperty unit = new SimpleStringProperty();
    private final FloatProperty unitPrice = new SimpleFloatProperty();
    private final FloatProperty totalPrice = new SimpleFloatProperty();

    public Delivery(DeliveryDto dto) {
        uid.set(dto.getUid());
        materialId.set(dto.getMaterial().getUid());
        materialName.set(dto.getMaterial().getName());
        store.set(dto.getInvoice().getShop().getName());
        invoiceNumber.set(dto.getInvoice().getInvoiceNumber());
        orderDate.set(dto.getInvoice().getOrderDate());
        plannedDate.set(dto.getInvoice().getPlannedDate());
        deliveryDate.set(dto.getInvoice().getDeliveryDate());
        qty.set(dto.getQty());
        unit.set(dto.getUnit().toValue());
        unitPrice.set(dto.getUnitPrice());
        totalPrice.set(dto.getTotalPrice());
    }
}
