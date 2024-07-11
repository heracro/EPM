package org.epm.invoice.model;

import org.epm.common.model.DataModel;

import java.time.LocalDate;

public abstract class InvoiceData<T extends InvoiceData<T>> implements DataModel {

    @Override
    public boolean isValidEntity() {
        return true;
    }

    private boolean isPaymentDueDateOk() {
        return getPaymentDueDate() == null
                || (getInvoiceDate() != null && !getPaymentDueDate().isBefore(getInvoiceDate()));
    }

    private boolean isPaymentDateOk() {
        return getPaymentDate() == null
                || (getInvoiceDate() != null && !getPaymentDate().isBefore(getInvoiceDate()));
    }

    private boolean isInvoiceDateOk() {
        return getInvoiceDate() == null
                || (getOrderDate() != null && !getInvoiceDate().isBefore(getOrderDate()));
    }

    private boolean isDeliveryDateOk() {
        return getDeliveryDate() == null
                || (getOrderDate() != null && !getDeliveryDate().isBefore(getOrderDate()));
    }



    public abstract LocalDate getOrderDate();
    public abstract void setOrderDate(LocalDate orderDate);
    public abstract LocalDate getDeliveryDate();
    public abstract void setDeliveryDate(LocalDate deliveryDate);
    public abstract LocalDate getInvoiceDate();
    public abstract void setInvoiceDate(LocalDate invoiceDate);
    public abstract LocalDate getPaymentDueDate();
    public abstract void setPaymentDueDate(LocalDate paymentDueDate);
    public abstract LocalDate getPaymentDate();
    public abstract void setPaymentDate(LocalDate paymentDate);
    public abstract String getInvoiceNumber();
    public abstract void setInvoiceNumber(String invoiceNumber);
    public abstract String getParcelTrackingNumber();
    public abstract void setParcelTrackingNumber(String parcelTrackingNumber);
    public abstract String getStore();
    public abstract void setStore(String store);
    public abstract Float getTotalAmount();
    public abstract void setTotalAmount(Float totalAmount);

}
