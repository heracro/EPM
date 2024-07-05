package org.epm.material.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.epm.common.model.IDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MaterialDTO implements IDTO {
    private String name;
    private String norm;
    private String datasheet;
    private String dimensions;
    private Float weight;
    private Integer totalQty;
    private Integer freeQty;
    private Unit unit;

    public boolean isValidDTO() {
        return name != null || norm != null || datasheet != null
                || weight != null || totalQty != null || freeQty != null
                || unit != null;
    }

    @Override
    public String toString() {
        return "Material {" + name + norm + ", qty: "
                + freeQty + "/" + totalQty + " " + unit + "}";
    }
}
