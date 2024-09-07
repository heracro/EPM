package hihi.content.task;

import com.fasterxml.jackson.annotation.JsonTypeName;
import hihi.content.common.dataModel.AbstractDto;
import hihi.content.enums.TaskStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@JsonTypeName("Task")
public class TaskDto extends AbstractDto {

    private String name;
    private String description;
    private LocalDate dueDate;
    private TaskStatus status;

    public TaskDto(Task task) {
        name = task.nameProperty().getValue();
        description = task.descriptionProperty().getValue();
        dueDate = task.dueDateProperty().getValue();
        status = task.statusProperty().getValue();
    }
}
