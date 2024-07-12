package org.epm.material.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.epm.common.model.EntityListener;
import org.epm.common.model.IEntity;
import org.epm.material.enums.Unit;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "materials")
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(EntityListener.class)
public class MaterialEntity extends MaterialData implements IEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long privateId;
    @Column(nullable = false)
    private String name;
    private String norm;
    private String datasheet;
    @Column(nullable = false)
    private String dimensions;
    @Column(length = 1023)
    private String description;
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
        return "Material {" + getName() + "(" + getId() + "), "
                + getNorm() + ", qty: " + getFreeQty() + "/" + getTotalQty() + " " + getUnit() + "}";
    }

}
