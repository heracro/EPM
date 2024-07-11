package org.epm.delivery.model;

import org.epm.common.model.DataModel;
import org.epm.delivery.enums.DeliveryStatus;
import org.epm.invoice.enums.InvoiceStatus;
import org.epm.invoice.model.InvoiceEntity;
import org.epm.material.model.MaterialEntity;

public abstract class DeliveryData<T extends DeliveryData<T>> implements DataModel {

    @Override
    public boolean isValidEntity() {
        return getMaterial() != null && isStatusOk() && isQtyOk() && arePricesOk();
    }

    private boolean isStatusOk() {
        if (getStatus() == null) return false;
        switch (getStatus()) {
            case SHOPPING_LIST -> {
                return getInvoice() == null && getUnitPrice() == null;
            }
            case ORDERED -> {
                return getInvoice() != null && getInvoice().getOrderDate() != null
                        && getUnitPrice() == null && getUnitPrice() > 0;
            }
            case DELIVERED, CANCELLED -> {
                return getInvoice() != null && getInvoice().getDeliveryDate() != null;
            }
            case COMPLAINT -> {
                return getInvoice() != null && getInvoice().getDeliveryDate() != null
                        && getInvoice().getStatus() == InvoiceStatus.DISPUTED;
            }
        }
        return false;
    }

    private boolean isQtyOk() {
        return getQty() != null && getQty() > 0;
    }

    private boolean arePricesOk() {
        boolean bothNull = getUnitPrice() == null && getTotalPrice() == null;
        boolean noneNull = getUnitPrice() != null && getTotalPrice() != null;
        boolean bothNotNegative = bothNull || (noneNull && getUnitPrice() >= 0
                && getTotalPrice() >= 0);
        boolean formulaMatches = noneNull && bothNotNegative && getQty() != null
                && (getUnitPrice() * getQty() == getTotalPrice());
        return bothNull || formulaMatches;
    }

    public abstract void setMaterial(MaterialEntity material);
    public abstract MaterialEntity getMaterial();
    public abstract void setStatus(DeliveryStatus status);
    public abstract DeliveryStatus getStatus();
    public abstract void setUnitPrice(Float unitPrice);
    public abstract Float getUnitPrice();
    public abstract void setTotalPrice(Float totalPrice);
    public abstract Float getTotalPrice();
    public abstract void setQty(Integer qty);
    public abstract Integer getQty();
    public abstract void setInvoice(InvoiceEntity invoice);
    public abstract InvoiceEntity getInvoice();
}
