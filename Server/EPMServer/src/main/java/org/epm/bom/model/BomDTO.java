package org.epm.bom.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.epm.bom.enums.BomStatus;
import org.epm.common.model.IDTO;
import org.epm.material.model.MaterialDTO;
import org.epm.project.model.ProjectDTO;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@JsonTypeName("Bom")
public class BomDTO extends BomData implements IDTO {

    private ProjectDTO project;
    private MaterialDTO material;
    private BomStatus status;
    private Integer qty;
    private Integer reservedQty;

    @Override
    public boolean isValidDTO() {
        return project != null || material != null
                || status != null || qty != null
                || reservedQty != null;
    }

    @Override
    public String toString() {
        return super.toString().substring(0, super.toString().length()-2)
                + ", action: " + getAction();
    }

    @Override
    public String getAction() {
        return null;
    }

}
