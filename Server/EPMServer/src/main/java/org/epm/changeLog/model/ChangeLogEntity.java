package org.epm.changeLog.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.epm.common.model.EntityListener;
import org.epm.common.model.IDependantEntity;
import org.epm.project.model.ProjectData;
import org.epm.project.model.ProjectEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "changeLogs")
@NoArgsConstructor
@EntityListeners(EntityListener.class)
public class ChangeLogEntity extends ChangeLogData implements IDependantEntity<ProjectEntity> {

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "uid", nullable = false)
    private ProjectEntity project;

    @Override
    public void setProject(ProjectData project) {
        this.project = (ProjectEntity) project;
    }

    @PrePersist
    public void setDefaults() {
        if (getTimestamp() == null) setTimestamp(LocalDateTime.now());
    }

    @Override
    public void setParent(ProjectEntity projectEntity) {
        setProject(projectEntity);
    }

    @Override
    public ProjectEntity getParent() {
        return getProject();
    }
}
