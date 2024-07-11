package org.epm.delivery.model;

import org.epm.common.model.DataModel;
import org.epm.delivery.enums.DeliveryStatus;
import org.epm.material.model.MaterialEntity;

import java.time.LocalDate;

public abstract class DeliveryData<T extends DeliveryData<T>> implements DataModel {

    public boolean areDatesOk() {
        return getDeliveryDate() == null
                || (getOrderDate() != null && getOrderDate().isAfter(getDeliveryDate()));
    }

    public boolean isStatusOk() {
//        switch (getStatus()) {
//            case SHOPPING_LIST ->
//        }
        return true;
    }

    @Override
    public boolean isValidEntity() {
        return getMaterial() != null && getStatus() != null && getUnitPrice() != null
                && getUnitPrice() >= 0 && getTotalPrice() != null && getTotalPrice() >= 0
                && getQty() != null && getQty() > 0 && getQty() * getUnitPrice() == getTotalPrice()
                && (getStore() == null || getStore().length() >= 4);
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
    public abstract void setOrderDate(LocalDate orderDate);
    public abstract LocalDate getOrderDate();
    public abstract void setDeliveryDate(LocalDate deliveryDate);
    public abstract LocalDate getDeliveryDate();
    public abstract void setStore(String store);
    public abstract String getStore();

}
