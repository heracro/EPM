package org.epm.bom.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.epm.common.model.IEntity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "boms")
@NoArgsConstructor
public class BomEntity extends BomData implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long privateId;

    @PrePersist
    @PreUpdate
    public void setDefaults() {
        if (getReservedQty() == null) setReservedQty(0);
    }

    @Override
    public String toString() {
        return super.toString().substring(0, super.toString().length()-2)
                + ", privateId: " + getPrivateId();
    }

}
