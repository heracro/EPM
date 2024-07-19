package org.epm.bom.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.epm.bom.enums.BomStatus;
import org.epm.common.model.IDependantEntity;
import org.epm.material.model.MaterialEntity;
import org.epm.project.model.ProjectEntity;

@Getter
@Setter
@Entity
@Table(name = "boms")
@TableGenerator(
        name = "bom_gen",
        table = "id_gen",
        pkColumnName = "gen_name",
        valueColumnName = "gen_val",
        pkColumnValue = "bom_id",
        allocationSize = 1)
@NoArgsConstructor
public class BomEntity extends BomData implements IDependantEntity<ProjectEntity> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_uid", referencedColumnName = "uid", nullable = false)
    private ProjectEntity project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "material_uid", referencedColumnName = "uid", nullable = false)
    private MaterialEntity material;

    @PrePersist
    @PreUpdate
    public void setDefaults() {
        if (getReservedQty() == null) setReservedQty(0);
        if (getStatus() == null) setStatus(BomStatus.MISSING);
    }

    @JsonIgnore
    @Override
    public boolean isValidEntity() {
        return getProject() != null && getMaterial() != null
                && getStatus() != null && getQty() != null && getQty() > 0;
    }

    @Override
    public void setParent(ProjectEntity parent) {
        setProject(parent);
    }

    @Override
    public ProjectEntity getParent() {
        return project;
    }

    @Override
    public Integer getProjectUid() {
        return project.getUid();
    }

    @Override
    public Integer getMaterialUid() {
        return material.getUid();
    }
}
