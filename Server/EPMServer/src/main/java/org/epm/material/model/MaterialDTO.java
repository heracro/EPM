package org.epm.material.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.epm.common.model.IDTO;

import java.util.Objects;
import java.util.Random;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeName("MaterialDTO")
public class MaterialDTO implements IDTO {
    private String name;
    private String norm;
    private String datasheet;
    private String dimensions;
    private Float weight;
    private Integer totalQty;
    private Integer freeQty;
    private Unit unit;

    @JsonIgnore
    public boolean isValidDTO() {
        return name != null || norm != null || datasheet != null
                || weight != null || totalQty != null || freeQty != null
                || unit != null;
    }

    @JsonIgnore
    @Override
    public String toString() {
        return "Material {" + name + ", " + norm + ", qty: "
                + freeQty + "/" + totalQty + " " + unit + "}";
    }

    public MaterialDTO randomInstance() {
        MaterialDTO m = new MaterialDTO();
        Random random = new Random();
        m.setName("Material " + random.nextInt(2000));
        m.setNorm((random.nextBoolean() ? "DIN" : "ISO") + (random.nextInt(1500) + 500));
        m.setDimensions(random.nextInt(200) + "x" + random.nextInt(160) + "x" + random.nextInt(40));
        m.setWeight(random.nextInt(30000) / 100f);
        m.setTotalQty(random.nextBoolean() || random.nextBoolean() ? random.nextInt(60) : 0);
        m.setFreeQty(random.nextBoolean() || random.nextBoolean() ? random.nextInt(m.getTotalQty()) : 0);
        m.setUnit(Unit.randomUnit());
        return m;
    }

    public static MaterialDTO emptyInstance() {
        return new MaterialDTO();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MaterialDTO that = (MaterialDTO) o;
        if (!Objects.equals(name, that.name)) return false;
        if (!Objects.equals(norm, that.norm)) return false;
        if (!Objects.equals(dimensions, that.dimensions)) return false;
        if (!Objects.equals(datasheet, that.datasheet)) return false;
        if (!Objects.equals(weight, that.weight)) return false;
        if (!Objects.equals(totalQty, that.totalQty)) return false;
        if (!Objects.equals(freeQty, that.freeQty)) return false;
        return Objects.equals(unit, that.unit);
    }
}
