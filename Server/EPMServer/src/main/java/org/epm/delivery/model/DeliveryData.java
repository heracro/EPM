package org.epm.delivery.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.epm.common.model.AbstractModuleData;
import org.epm.delivery.enums.DeliveryStatus;
import org.epm.invoice.enums.InvoiceStatus;
import org.epm.invoice.model.InvoiceData;
import org.epm.material.enums.Unit;
import org.epm.material.model.MaterialData;

@Slf4j
@Getter
@Setter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@MappedSuperclass
public abstract class DeliveryData extends AbstractModuleData {

    @Column(nullable = false, unique = true)
    private Integer uid;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    @Column(nullable = false)
    private Float unitPrice;

    @Column(nullable = false)
    private Float totalPrice;

    @Column(nullable = false)
    private Integer qty;

    @Column(nullable = false)
    private Unit unit;

    @JsonIgnore
    public boolean isValidEntity() {
        return getMaterial() != null && isStatusOk() && isQtyOk() && arePricesOk();
    }

    @JsonIgnore
    public boolean isValidDTO() {
        return getMaterial() != null || getStatus() != null || getUnitPrice() != null
                || getTotalPrice() != null || getQty() != null || getInvoice() != null;
    }

    void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
        if (qty == null) qty = 0;
        this.totalPrice = unitPrice * qty;
    }

    void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
        if (qty == null) qty = 0;
        this.unitPrice = totalPrice / qty;
    }

    private boolean isStatusOk() {
        if (getStatus() == null) return false;
        switch (getStatus()) {
            case SHOPPING_LIST -> {
                return getInvoice() == null && getUnitPrice() == null;
            }
            case ORDERED -> {
                return getInvoice() != null && getInvoice().getOrderDate() != null
                        && getUnitPrice() != null && getUnitPrice() > 0;
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

    public String toString() {
        return "Delivery {"
                + " uid: " + getUid()
                + ", id: " + getId()
                + ", status: " + getStatus()
                + ", unitPrice: " + getUnitPrice()
                + ", totalPrice: " + getTotalPrice()
                + ", qty: " + getQty()
                + ", unit: " + getUnit()
                + ", unitPrice: " + getUnitPrice()
                + ", totalPrice: " + getTotalPrice()
                + ", qty: " + getQty()
                + ", status: " + getStatus()
                + "}";
    }

    public abstract MaterialData getMaterial();
    public abstract InvoiceData getInvoice();
}
