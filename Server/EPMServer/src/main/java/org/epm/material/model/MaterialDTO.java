package org.epm.material.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.epm.common.model.IDTO;
import org.epm.material.enums.Unit;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeName("Material")
public class MaterialDTO extends MaterialData implements IDTO {

    private String name;
    private String norm;
    private String datasheet;
    private String dimensions;
    private String description;
    private Float weight;
    private Integer totalQty;
    private Integer freeQty;
    private Unit unit;
    private String action;

    @Override
    @JsonIgnore
    public boolean isValidDTO() {
        return name != null || norm != null || datasheet != null
                || dimensions != null || description != null
                || weight != null || totalQty != null || freeQty != null
                || unit != null;
    }

    @JsonIgnore
    @Override
    public String toString() {
        return "Material {" + name + ", " + norm + ", qty: "
                + freeQty + "/" + totalQty + " " + unit + "}";
    }

}
