package org.epm.changeLog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.epm.changeLog.enums.ChangeLogSource;
import org.epm.changeLog.enums.ChangeType;
import org.epm.common.model.DataModel;
import org.epm.project.model.ProjectData;

import java.time.LocalDateTime;

@Slf4j
@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public abstract class ChangeLogData implements DataModel {

    @Column(nullable = false, unique = true)
    private Integer id;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private ProjectData project;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ChangeLogSource source;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ChangeType changeType;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(length = 63)
    private String author;

    @JsonIgnore
    @Override
    public boolean isValidEntity() {
        return getId() != null && getProject() != null
                && getSource() != null && getChangeType() != null
                && getDescription() != null;
    }

    @JsonIgnore
    @Override
    public boolean isValidDTO() {
        return getId() != null || getTimestamp() != null
                || getProject() != null || getSource() != null
                || getChangeType() != null || getDescription() != null
                || getAuthor() != null;
    }

    public String toString() {
        return "ChangeLog {" + getId() + ", " + getTimestamp() + ", Project: "
                + getProject().getId() + ", " + getSource() + ", " + getChangeType() + ", "
                + getAuthor() + "}";
    }

}
