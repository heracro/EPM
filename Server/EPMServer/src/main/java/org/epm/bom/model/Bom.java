package org.epm.bom.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.epm.material.model.MaterialEntity;
import org.epm.project.model.Project;

@Data
@Entity
@NoArgsConstructor
public class Bom {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "material_id")
    private MaterialEntity materialEntity;

    @Enumerated(EnumType.STRING)
    private BomStatus status;

    private Integer qty;
    private Integer reservedQty;

    public String toString() {
        return "Bom{(" + id + "), "
                + project.getName() + "(" + project.getId() + "), "
                + materialEntity.getName() + "(" + materialEntity.getId() + "), total "
                + qty + ", res. " + reservedQty + "}";
    }
}
