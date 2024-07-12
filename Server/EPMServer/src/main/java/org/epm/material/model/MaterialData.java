package org.epm.material.model;

import lombok.Data;
import org.epm.common.model.DataModel;
import org.epm.material.enums.Unit;

@Data
public abstract class MaterialData implements DataModel {

    private Integer id;

    @Override
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

    @Override
    public boolean isValidDTO() {
        return false;
    }

    public abstract String getName();
    public abstract String getNorm();
    public abstract String getDatasheet();
    public abstract String getDimensions();
    public abstract Float getWeight();
    public abstract Integer getTotalQty();
    public abstract Integer getFreeQty();
    public abstract Unit getUnit();
}
