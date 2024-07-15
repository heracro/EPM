package org.epm.task.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.epm.common.model.AbstractModuleData;
import org.epm.project.model.ProjectData;
import org.epm.task.enums.TaskStatus;

import java.time.LocalDate;

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
    private LocalDate dueDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    public String toString() {
        return "Task {"
                + "uid: " + getUid()
                + ", id: " + getId()
                + ", name: " + getName()
                + ", dueDate: " + getDueDate()
                + ", status: " + getStatus()
                + ", project: " + getProject().getName()
                + "}";
    }

    @JsonIgnore
    public boolean isValidEntity() {
        throw new UnsupportedOperationException("Not supported yet.");
    }



    public abstract ProjectData getProject();
}
