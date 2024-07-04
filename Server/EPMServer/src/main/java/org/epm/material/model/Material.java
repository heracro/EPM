package org.epm.material.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Material {
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
    private Unit qtyUnit;

    @Override
    public String toString() {
        return "Material {" + name + "(" + id + "), "
                + norm + ", qty: " + freeQty + "/" + totalQty + " " + qtyUnit + "}";
    }

    public boolean isValidEntity() {
        return name != null && name.length() > 3
                && dimensions != null && !dimensions.isEmpty() && !dimensions.isBlank()
                && weight != null && weight >= 0
                && totalQty != null && totalQty >= 0
                && freeQty != null && freeQty >= 0
                && qtyUnit != null;
    }

    public boolean isValidDTO() {
        return name != null || norm != null || datasheet != null
                || weight != null || totalQty != null || freeQty != null
                || qtyUnit != null;
    }

    public boolean hasValidValues() {
        return isValidEntity() && name.length() > 3 && weight >= 0 && totalQty >= 0
                && freeQty >= 0 && (norm == null || norm.length() > 3)
                && (datasheet == null || datasheet.length() > 3);
    }
}
