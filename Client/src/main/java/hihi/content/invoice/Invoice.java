package hihi.content.invoice;

import hihi.content.common.AbstractContent;
import hihi.content.shop.Shop;
import hihi.content.enums.InvoiceStatus;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class Invoice extends AbstractContent {

    private ObjectProperty<LocalDate> orderDate;
    private ObjectProperty<LocalDate> plannedDate;
    private ObjectProperty<LocalDate> deliveryDate;
    private ObjectProperty<LocalDate> invoiceDate;
    private ObjectProperty<LocalDate> paymentDueDate;
    private ObjectProperty<LocalDate> paymentDate;
    private StringProperty invoiceNumber;
    private StringProperty parcelTrackingNumber;
    private FloatProperty totalAmount;
    private ObjectProperty<InvoiceStatus> status;
    private ObjectProperty<Shop> shop;

    public Invoice(InvoiceDto dto) {
        orderDate.set(dto.getOrderDate());
        plannedDate.set(dto.getPlannedDate());
        deliveryDate.set(dto.getDeliveryDate());
        invoiceDate.set(dto.getInvoiceDate());
        paymentDueDate.set(dto.getPaymentDueDate());
        paymentDate.set(dto.getPaymentDate());
        invoiceNumber.set(dto.getInvoiceNumber());
        parcelTrackingNumber.set(dto.getParcelTrackingNumber());
        totalAmount.set(dto.getTotalAmount());
        status.set(dto.getStatus());
        shop.set(new Shop(dto.getShop()));
    }

    public ObjectProperty<LocalDate> orderDateProperty() { return orderDate; }
    public ObjectProperty<LocalDate> plannedDateProperty() { return plannedDate; }
    public ObjectProperty<LocalDate> deliveryDateProperty() { return deliveryDate; }
    public ObjectProperty<LocalDate> invoiceDateProperty() { return invoiceDate; }
    public ObjectProperty<LocalDate> paymentDueDateProperty() { return paymentDueDate; }
    public ObjectProperty<LocalDate> paymentDateProperty() { return paymentDate; }
    public StringProperty invoiceNumberProperty() { return invoiceNumber; }
    public StringProperty parcelTrackingNumberProperty() { return parcelTrackingNumber; }
    public FloatProperty totalAmountProperty() { return totalAmount; }
    public ObjectProperty<InvoiceStatus> statusProperty() { return status; }
    public ObjectProperty<Shop> shopProperty() { return shop; }

}
