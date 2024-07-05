package org.epm.delivery.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.epm.common.model.IEntity;
import org.epm.material.model.MaterialEntity;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
public class DeliveryEntity implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "material_id")
    private MaterialEntity materialEntity;

    @NotNull
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    @NotNull
    @PositiveOrZero
    private Float unitPrice;

    @NotNull
    @PositiveOrZero
    private Float totalPrice;

    @NotNull
    @PositiveOrZero
    private Integer qty;

    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private String store;

    public boolean isValidEntity() {
        return materialEntity != null && status != null && unitPrice != null
                && unitPrice >= 0 && totalPrice != null && totalPrice >= 0
                && qty != null && qty > 0 && qty * unitPrice == totalPrice
                && (store == null || store.length() >= 4);
    }

    public boolean isValidDTO() {
        return materialEntity != null || status != null || unitPrice != null
                || totalPrice != null || qty != null || orderDate != null
                || deliveryDate != null || store != null;
    }
}
