package org.epm.bom.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.epm.common.model.IDTO;
import org.epm.common.utils.RandomUtils;
import org.epm.material.model.MaterialDTO;
import org.epm.project.model.ProjectDTO;

@Data
@NoArgsConstructor
@JsonTypeName("BomDTO")
public class BomDTO implements IDTO {
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

    public static BomDTO randomInstance() {
        BomDTO bom = new BomDTO();
        bom.setQty(RandomUtils.randomInt(13));
        return bom;
    }

    public static BomDTO emptyInstance() {
        return new BomDTO();
    }

    public void propagateRandomProject(ProjectDTO project) {
        this.project = project;
        this.status = BomStatus.randomBomStatus(project.getStatus());
        switch (status) {
            case COMPLETE, TAKEN -> setReservedQty(getQty());
            case PARTIAL -> setReservedQty(RandomUtils.randomInt(10) + 1);
            case MISSING -> setReservedQty(0);
        }
    }
}
