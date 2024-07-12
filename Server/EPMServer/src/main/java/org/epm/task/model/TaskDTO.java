package org.epm.task.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.epm.common.model.IDTO;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@JsonTypeName("Task")
public class TaskDTO extends TaskData implements IDTO {

    private String action;

}
