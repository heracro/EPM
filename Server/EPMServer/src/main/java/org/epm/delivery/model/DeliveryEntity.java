package org.epm.delivery.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.epm.common.model.IEntity;
import org.epm.delivery.enums.DeliveryStatus;
import org.epm.invoice.model.InvoiceEntity;
import org.epm.material.model.MaterialEntity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "deliveries")
@NoArgsConstructor
public class DeliveryEntity
        extends DeliveryData<DeliveryEntity> implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "material_id", nullable = false)
    private MaterialEntity material;
    @Column(nullable = false) @Enumerated(EnumType.STRING)
    private DeliveryStatus status;
    @Column(nullable = false)
    private Float unitPrice;
    @Column(nullable = false)
    private Float totalPrice;
    @Column(nullable = false)
    private Integer qty;
    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private InvoiceEntity invoice;

}
