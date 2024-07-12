package org.epm.bom.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.epm.bom.enums.BomStatus;
import org.epm.common.model.DataModel;
import org.epm.material.model.MaterialEntity;
import org.epm.project.model.ProjectEntity;

@Slf4j
@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public abstract class BomData implements DataModel {

    private Integer id;
    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private ProjectEntity project;
    @ManyToOne @JoinColumn(name = "material_id", nullable = false)
    private MaterialEntity material;
    @Enumerated(EnumType.STRING) @Column(nullable = false)
    private BomStatus status;
    @Column(nullable = false)
    private Integer qty;
    private Integer reservedQty;

    @JsonIgnore
    @Override
    public boolean isValidEntity() {
        return getProject() != null && getMaterial() != null
                && getStatus() != null && getQty() != null && getQty() > 0;
    }

    @JsonIgnore
    @Override
    public boolean isValidDTO() {
        return false;
    }

    public String toString() {
        return "Bom{(" + getId() + "), "
                + project.getName() + "(" + project.getId() + "), "
                + material.getName() + "(" + material.getId() + "), tot. "
                + qty + ", res. " + reservedQty + "}";
    }

}
