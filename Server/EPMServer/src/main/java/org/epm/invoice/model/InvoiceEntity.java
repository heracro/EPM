package org.epm.invoice.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.epm.common.model.IEntity;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "invoices")
@NoArgsConstructor
public class InvoiceEntity
        extends InvoiceData<InvoiceEntity> implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private LocalDate invoiceDate;
    private LocalDate paymentDueDate;
    private LocalDate paymentDate;
    private String invoiceNumber;
    private String parcelTrackingNumber;
    private String store;
    private Float totalAmount;
}
