package org.epm.task.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.epm.common.model.IDTO;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@JsonTypeName("TaskDTO")
public class TaskDTO extends TaskData<TaskDTO> implements IDTO {


    @JsonIgnore
    @Override
    public boolean isValidDTO() {
        return false;
    }
}
