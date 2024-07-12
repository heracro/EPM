package org.epm.invoice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.epm.common.model.DataModel;
import org.epm.delivery.model.DeliveryEntity;
import org.epm.invoice.enums.InvoiceStatus;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Getter
@Setter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public abstract class InvoiceData implements DataModel {

    @Column(nullable = false, unique = true)
    private Integer id;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate orderDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate deliveryDate;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate invoiceDate;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate paymentDueDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate paymentDate;

    @Column(nullable = false)
    private String invoiceNumber;

    private String parcelTrackingNumber;

    @Column(nullable = false)
    private String store;

    @Column(nullable = false)
    private Float totalAmount;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private InvoiceStatus status;

    @JsonIgnore
    @OneToMany(mappedBy = "invoice", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DeliveryEntity> deliveries;

    @JsonIgnore
    @Override
    public boolean isValidEntity() {
        return getOrderDate() != null && getStore() != null
                && areDatesOk() && isStatusOk();
    }

    @JsonIgnore
    @Override
    public boolean isValidDTO() {
        return getOrderDate() != null || getDeliveryDate() != null || getInvoiceDate() != null
                || getPaymentDueDate() != null || getPaymentDate() != null
                || getInvoiceNumber() != null || getParcelTrackingNumber() != null
                || getStore() != null || getTotalAmount() != null
                || getStatus() != null;
    }

    private boolean areDatesOk() {
        return isPaymentDueDateOk() && isInvoiceDateOk() && isPaymentDateOk() && isDeliveryDateOk();
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
                || (!getInvoiceDate().isBefore(getOrderDate()));
    }

    private boolean isDeliveryDateOk() {
        return getDeliveryDate() == null
                || (getOrderDate() != null && !getDeliveryDate().isBefore(getOrderDate())
                    && getParcelTrackingNumber() != null);
    }

    private boolean isStatusOk() {
        switch (getStatus()) {
            case UNISSUED -> {
                return getInvoiceDate() == null; }
            case ISSUED -> {
                return getInvoiceDate() != null && getPaymentDate() == null
                        && getPaymentDueDate() != null && getPaymentDueDate().isAfter(LocalDate.now()); }
            case PAID -> {
                return getInvoiceDate() != null && getPaymentDueDate() != null
                        && getPaymentDate() != null && getPaymentDueDate().isBefore(getPaymentDueDate());
            }
            case EXPIRED -> {
                return getInvoiceDate() != null && getPaymentDueDate() != null
                        && getPaymentDueDate().isBefore(
                                getPaymentDate() == null ? LocalDate.now() : getPaymentDueDate());
            }
            case DISPUTED, CORRECTED -> {
                return getInvoiceDate() != null && getDeliveryDate() != null;
            }
            case CANCELLED -> {
                return getInvoiceDate() != null;
            }
        }
        return false;
    }

}
