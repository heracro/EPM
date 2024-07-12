package org.epm.delivery.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.epm.common.model.EntityListener;
import org.epm.common.model.IEntity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "deliveries")
@NoArgsConstructor
@EntityListeners(EntityListener.class)
public class DeliveryEntity extends DeliveryData implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long privateId;

    @PrePersist
    @PreUpdate
    public void setDefaults() {
        if (getUnitPrice() == null) setUnitPrice(0f);
        if (getTotalPrice() == null) setTotalPrice(0f);
    }

    @Override
    public String toString() {
        return super.toString().substring(0, super.toString().length()-2)
                + ", privateId: " + getPrivateId() + "}";
    }

}
