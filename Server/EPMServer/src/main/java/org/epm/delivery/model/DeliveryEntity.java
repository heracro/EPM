package org.epm.delivery.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.epm.common.configuration.Config;
import org.epm.common.model.IEntity;
import org.epm.common.utils.RandomUtils;
import org.epm.delivery.enums.DeliveryStatus;
import org.epm.invoice.model.InvoiceEntity;
import org.epm.material.model.MaterialEntity;

import java.time.LocalDate;

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
    @ManyToOne @JoinColumn(name = "material_id", nullable = false)
    private MaterialEntity material;
    @Column(nullable = false) @Enumerated(EnumType.STRING)
    private DeliveryStatus status;
    @Column(nullable = false)
    private Float unitPrice;
    @Column(nullable = false)
    private Float totalPrice;
    @Column(nullable = false)
    private Integer qty;
    @ManyToOne @JoinColumn(name = "invoice_id", nullable = false)
    private InvoiceEntity invoice;

    public static DeliveryEntity randomInstance() {
        DeliveryEntity d = new DeliveryEntity();
        d.setQty(RandomUtils.randomInt(20));
        d.setStatus(DeliveryStatus.randomDeliveryStatus());
        if (d.getStatus() != DeliveryStatus.SHOPPING_LIST) {
            d.setUnitPrice(RandomUtils.randomInt(10000) / 100f);
            d.setTotalPrice(d.getUnitPrice() * d.getQty());
        }
        switch (d.getStatus()) {
            case DELIVERED:
                d.setDeliveryDate(RandomUtils.randomDate(Config.BIG_BANG_DATE, LocalDate.now().minusDays(2)));
            case ORDERED:
                d.setOrderDate(RandomUtils.randomDate(Config.BIG_BANG_DATE, d.getDeliveryDate() != null ? d.getDeliveryDate().minusDays(4) : LocalDate.now()));
        }
        d.setStore("Store " + RandomUtils.randomInt(120));
        return d;
    }

}
