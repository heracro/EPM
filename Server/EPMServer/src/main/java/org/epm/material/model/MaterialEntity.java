package org.epm.material.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.epm.common.model.EntityListener;
import org.epm.common.model.IEntity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "materials")
@NoArgsConstructor
@EntityListeners(EntityListener.class)
public class MaterialEntity extends MaterialData implements IEntity {

    public boolean isValidEntity() {
        return getName() != null && getName().length() > 3
                && (getNorm() == null || getNorm().length() > 3)
                && (getDatasheet() == null || getDatasheet().length() > 4)
                && getDimensions() != null && getDimensions().length() > 1
                && getWeight() != null && getWeight() >= 0
                && getTotalQty() != null && getTotalQty() >= 0
                && getFreeQty() != null && getFreeQty() >= 0
                && getFreeQty() <= getTotalQty() && getUnit() != null;
    }
}
