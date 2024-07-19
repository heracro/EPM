package org.epm.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.epm.bom.model.BomDTO;
import org.epm.common.model.IDTO;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@JsonTypeName("Project")
public class ProjectDTO extends ProjectData implements IDTO {

    private String action;

    @JsonIgnore
    private List<BomDTO> boms;

    @JsonIgnore
    public boolean isValidDTO() {
        return getUid() != null || getName() != null || getBody() != null || getPlannedStartDate() != null
                || getPlannedEndDate() != null || getRealStartDate() != null
                || getRealEndDate() != null || getWorkingHoursCount() != null
                || getCause() != null || getMaterialsReadyDate() != null
                || getProjectLocation() != null || getLocationType() != null
                || getStatus() != null || getAction() != null;
    }

}
