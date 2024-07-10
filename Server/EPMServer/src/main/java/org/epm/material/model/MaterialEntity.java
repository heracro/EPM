package org.epm.material.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.epm.common.model.IEntity;

import java.util.Random;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MaterialEntity implements IEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String norm;
    private String datasheet;
    @Column(nullable = false)
    private String dimensions;
    @Column(nullable = false)
    private Float weight;
    @Column(nullable = false)
    private Integer totalQty;
    @Column(nullable = false)
    private Integer freeQty;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Unit unit;

    @Override
    public String toString() {
        return "Material {" + name + "(" + id + "), "
                + norm + ", qty: " + freeQty + "/" + totalQty + " " + unit + "}";
    }

    public boolean isValidEntity() {
        return name != null && name.length() > 3
                && (norm == null || norm.length() > 3)
                && (datasheet == null || datasheet.length() > 4)
                && dimensions != null && dimensions.length() > 1
                && weight != null && weight >= 0
                && totalQty != null && totalQty >= 0
                && freeQty != null && freeQty >= 0
                && unit != null;
    }

    public static MaterialEntity randomInstance() {
        MaterialEntity m = new MaterialEntity();
        Random random = new Random();
        m.setName("Material " + random.nextInt(2000));
        m.setNorm((random.nextBoolean() ? "DIN" : "ISO") + (random.nextInt(1500) + 500));
        m.setDimensions("" + random.nextInt(200) + "x" + random.nextInt(160) + "x" + random.nextInt(40));
        m.setWeight(random.nextInt(30000) / 100f);
        m.setTotalQty(random.nextBoolean() || random.nextBoolean() ? random.nextInt(60) : 0);
        m.setFreeQty((random.nextBoolean() || random.nextBoolean()) && m.getTotalQty() > 0 ? random.nextInt(m.getTotalQty()) : 0);
        m.setUnit(Unit.randomUnit());
        return m;
    }
}
