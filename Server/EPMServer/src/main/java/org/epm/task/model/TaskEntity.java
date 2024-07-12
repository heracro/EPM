package org.epm.task.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.epm.common.model.IEntity;
import org.epm.project.model.ProjectEntity;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "tasks")
@NoArgsConstructor
public class TaskEntity extends TaskData implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long privateId;
    private String name;
    private String description;
    private LocalDateTime dueDate;
    private boolean completed;

    @ManyToOne
    @JoinColumn//(name = "project_id")
    private ProjectEntity project;
}
