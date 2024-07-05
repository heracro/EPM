package org.epm.material.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.epm.common.model.IEntity;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MaterialEntity implements IEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotNull
    @Size(min = 4)
    private String name;
    private String norm;
    private String datasheet;
    @NotNull
    @Size(min = 2)
    private String dimensions;
    @NotNull
    @Positive
    private Float weight;
    @NotNull
    @PositiveOrZero
    private Integer totalQty;
    @NotNull
    @PositiveOrZero
    private Integer freeQty;
    @NotNull
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

}
