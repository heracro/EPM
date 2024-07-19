package org.epm.task.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.epm.common.model.IDependantEntity;
import org.epm.project.model.ProjectEntity;

@Getter
@Setter
@Entity
@Table(name = "tasks")
@TableGenerator(
        name = "task_gen",
        table = "id_gen",
        pkColumnName = "gen_name",
        valueColumnName = "gen_val",
        pkColumnValue = "task_id",
        allocationSize = 1)
@NoArgsConstructor
public class TaskEntity extends TaskData implements IDependantEntity<ProjectEntity> {

    @ManyToOne
    @JoinColumn(name = "project_uid", referencedColumnName = "uid")
    private ProjectEntity project;

    @Override
    public void setParent(ProjectEntity projectEntity) {
        setProject(projectEntity);
    }

    @Override
    public ProjectEntity getParent() {
        return getProject();
    }
}
