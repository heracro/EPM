package org.epm.task.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;
import org.epm.common.model.IDTO;
import org.epm.project.model.ProjectDTO;
import org.epm.project.model.ProjectData;

@Getter
@Setter
@NoArgsConstructor
@JsonTypeName("Task")
public class TaskDTO extends TaskData implements IDTO {

    private ProjectDTO project;
    private String action;

    @Override
    public void setProject(ProjectData project) {
        this.project = (ProjectDTO) project;
    }
}
