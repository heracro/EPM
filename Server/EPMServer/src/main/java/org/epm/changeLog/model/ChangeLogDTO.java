package org.epm.changeLog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.epm.common.model.IDTO;
import org.epm.project.model.ProjectDTO;
import org.epm.project.model.ProjectData;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@JsonTypeName("ChangeLog")
public class ChangeLogDTO extends ChangeLogData implements IDTO {

    private ProjectDTO project;

    private String action;

    @JsonIgnore
    @Override
    public boolean isValidDTO() {
        return super.isValidDTO() || getAction() != null;
    }

    @Override
    public void setProject(ProjectData project) {
        this.project = (ProjectDTO) project;
    }
}
