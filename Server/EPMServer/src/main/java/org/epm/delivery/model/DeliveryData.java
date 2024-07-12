package org.epm.delivery.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.epm.common.model.DataModel;
import org.epm.delivery.enums.DeliveryStatus;
import org.epm.invoice.enums.InvoiceStatus;
import org.epm.invoice.model.InvoiceEntity;
import org.epm.material.enums.Unit;
import org.epm.material.model.MaterialEntity;

@Slf4j
@Getter
@Setter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public abstract class DeliveryData implements DataModel {

    @Column(nullable = false, unique = true)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "material_id", nullable = false)
    private MaterialEntity material;

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

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private InvoiceEntity invoice;

    @JsonIgnore
    @Override
    public boolean isValidEntity() {
        return getMaterial() != null && isStatusOk() && isQtyOk() && arePricesOk();
    }

    @JsonIgnore
    @Override
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
        return "Delivery {" + getId() + ", " + getMaterial().getName()
                + ", qty: " + getQty() + " " + getUnit() + ", unitPrice: " + getUnitPrice()
                + ", totalPrice: " + getTotalPrice() + ", status: " + getStatus() + ", bill no.: "
                + (getInvoice() == null ? "null" : getInvoice().getInvoiceNumber()) + "}";
    }

}
