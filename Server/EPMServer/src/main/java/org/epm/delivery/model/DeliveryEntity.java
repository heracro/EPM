package org.epm.delivery.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.epm.common.Config;
import org.epm.common.model.IEntity;
import org.epm.common.utils.RandomUtils;
import org.epm.material.model.MaterialEntity;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "deliveries")
@NoArgsConstructor
public class DeliveryEntity implements IEntity {

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
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private String store;

    public boolean isValidEntity() {
        return material != null && status != null && unitPrice != null
                && unitPrice >= 0 && totalPrice != null && totalPrice >= 0
                && qty != null && qty > 0 && qty * unitPrice == totalPrice
                && (store == null || store.length() >= 4);
    }

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
