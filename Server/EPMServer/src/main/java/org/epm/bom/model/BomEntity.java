package org.epm.bom.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.epm.common.model.IEntity;
import org.epm.common.utils.RandomUtils;
import org.epm.material.model.MaterialEntity;
import org.epm.project.model.ProjectEntity;

@Data
@Entity
@NoArgsConstructor
public class BomEntity implements IEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne @JoinColumn(name = "project_id", nullable = false)
    private ProjectEntity project;
    @ManyToOne @JoinColumn(name = "material_id", nullable = false)
    private MaterialEntity material;
    @Enumerated(EnumType.STRING) @Column(nullable = false)
    private BomStatus status;
    @Column(nullable = false)
    private Integer qty;
    private Integer reservedQty;

    public String toString() {
        return "Bom{(" + id + "), "
                + project.getName() + "(" + project.getId() + "), "
                + material.getName() + "(" + material.getId() + "), total "
                + qty + ", res. " + reservedQty + "}";
    }

    @Override
    public boolean isValidEntity() {
        return project != null && material != null
                && status != null && qty != null;
    }

    public static BomEntity randomInstance() {
        BomEntity bom = new BomEntity();
        bom.setQty(RandomUtils.randomInt(13));
        return bom;
    }

    public void propagateRandomProject(ProjectEntity project) {
        this.project = project;
        this.status = BomStatus.randomBomStatus(project.getStatus());
        switch (status) {
            case COMPLETE, TAKEN -> setReservedQty(getQty());
            case PARTIAL -> setReservedQty(RandomUtils.randomInt(10) + 1);
            case MISSING -> setReservedQty(0);
        }
    }
}
