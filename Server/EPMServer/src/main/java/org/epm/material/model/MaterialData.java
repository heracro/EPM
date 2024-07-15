package org.epm.material.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.epm.common.model.AbstractModuleData;
import org.epm.material.enums.Unit;

@Getter
@Setter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@MappedSuperclass
public abstract class MaterialData extends AbstractModuleData {

    @Column(nullable = false, unique = true)
    private Integer uid;

    @Column(nullable = false)
    private String name;

    private String norm;

    private String datasheet;

    @Column(nullable = false)
    private String dimensions;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private Float weight;

    @Column(nullable = false)
    private Integer totalQty;

    @Column(nullable = false)
    private Integer freeQty;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private Unit unit;

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

    public String toString() {
        return "Material {"
                + " uid: " + getUid()
                + " id: " + getId()
                + " name: " + getName()
                + " norm: " + getNorm()
                + " datasheet: " + getDatasheet()
                + " dimensions: " + getDimensions()
                + " weight: " + getWeight()
                + " totalQty: " + getTotalQty()
                + " freeQty: " + getFreeQty()
                + " unit: " + getUnit()
                + "}";
    }

}
