package org.epm.task.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.epm.common.model.AbstractModuleData;
import org.epm.project.model.ProjectData;
import org.epm.task.enums.TaskStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@MappedSuperclass
public abstract class TaskData extends AbstractModuleData {

    @Column(nullable = false, unique = true)
    private Integer uid;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime dueDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    public String toString() {
        return "\n\tTask {"
                + "\n\tuid: " + getUid()
                + ",\n\tid: " + getId()
                + ",\n\tname: " + getName()
                + ",\n\tdueDate: " + getDueDate()
                + ",\n\tstatus: " + getStatus()
                + ",\n\tproject: " + getProject().getName()
                + "\n\t}";
    }

    @JsonIgnore
    public boolean isValidEntity() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @JsonIgnore
    public boolean isValidDTO() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public abstract ProjectData getProject();
    public abstract void setProject(ProjectData project);
}
