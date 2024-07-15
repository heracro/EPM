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

    public String toString() {
        return "\n\tMaterial {"
                + "\n\t\tuid: " + getUid()
                + "\n\t\tid: " + getId()
                + "\n\t\tname: " + getName()
                + "\n\t\tnorm: " + getNorm()
                + "\n\t\tdatasheet: " + getDatasheet()
                + "\n\t\tdimensions: " + getDimensions()
                + "\n\t\tweight: " + getWeight()
                + "\n\t\ttotalQty: " + getTotalQty()
                + "\n\t\tfreeQty: " + getFreeQty()
                + "\n\t\tunit: " + getUnit()
                + "\n\t}";
    }

}
