package hihi.content.task;

import hihi.content.common.dataModel.AbstractContent;
import hihi.content.enums.TaskStatus;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class Task extends AbstractContent {

    private StringProperty name = new SimpleStringProperty();
    private StringProperty description = new SimpleStringProperty();
    private ObjectProperty<LocalDate> dueDate = new SimpleObjectProperty<>();
    private ObjectProperty<TaskStatus> status = new SimpleObjectProperty<>();

    public Task(TaskDto dto) {
        name.set(dto.getName());
        description.set(dto.getDescription());
        dueDate.set(dto.getDueDate());
        status.set(dto.getStatus());
    }

    public StringProperty nameProperty() { return name; }
    public StringProperty descriptionProperty() { return description; }
    public ObjectProperty<LocalDate> dueDateProperty() { return dueDate; }
    public ObjectProperty<TaskStatus> statusProperty() { return status; }
}
