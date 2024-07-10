package org.epm.material.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.epm.common.model.IDTO;

import java.util.Random;

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

    public MaterialDTO randomInstance() {
        MaterialDTO m = new MaterialDTO();
        Random random = new Random();
        m.setName("Material " + random.nextInt(2000));
        m.setNorm((random.nextBoolean() ? "DIN" : "ISO") + (random.nextInt(1500) + 500));
        m.setDimensions("" + random.nextInt(200) + "x" + random.nextInt(160) + "x" + random.nextInt(40));
        m.setWeight(random.nextInt(30000) / 100f);
        m.setTotalQty(random.nextBoolean() || random.nextBoolean() ? random.nextInt(60) : 0);
        m.setFreeQty(random.nextBoolean() || random.nextBoolean() ? random.nextInt(m.getTotalQty()) : 0);
        m.setUnit(Unit.randomUnit());
        return m;
    }
}
