package org.epm.task.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.epm.common.model.IDTO;
import org.epm.project.model.ProjectDTO;

@Getter
@Setter
@NoArgsConstructor
@JsonTypeName("Task")
public class TaskDTO extends TaskData implements IDTO {

    private ProjectDTO project;
    private String action;

    @JsonIgnore
    public boolean isValidDTO() {
        return getName() != null || getDescription() != null || getDueDate() != null
                || getStatus() != null || getProject() != null;
    }

}
