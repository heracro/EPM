package org.epm.changeLog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.epm.changeLog.enums.ChangeLogSource;
import org.epm.changeLog.enums.ChangeType;
import org.epm.common.model.AbstractModuleData;
import org.epm.project.model.ProjectData;

import java.time.LocalDateTime;

@Slf4j
@Getter
@Setter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@MappedSuperclass
public abstract class ChangeLogData extends AbstractModuleData {

    @Column(nullable = false, unique = true)
    private Integer uid;

    @Column(nullable = false)
    private LocalDateTime timestamp;

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
    public boolean isValidEntity() {
        return getId() != null && getProject() != null
                && getSource() != null && getChangeType() != null
                && getDescription() != null;
    }

    @JsonIgnore
    public boolean isValidDTO() {
        return getId() != null || getTimestamp() != null
                || getProject() != null || getSource() != null
                || getChangeType() != null || getDescription() != null
                || getAuthor() != null;
    }

    public String toString() {
        return "ChangeLog {"
                + " Uid: " + getUid()
                + ", id: " + getId()
                + ", Timestamp: " + getTimestamp()
                + ", Source: " + getSource()
                + ", ChangeType: " + getChangeType()
                + ", Author: " + getAuthor()
                + "}";
    }

    public abstract ProjectData getProject();
}
