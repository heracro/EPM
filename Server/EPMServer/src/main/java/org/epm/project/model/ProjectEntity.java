package org.epm.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.epm.common.configuration.Config;
import org.epm.common.model.IEntity;
import org.epm.project.enums.LocationType;
import org.epm.project.enums.ProjectCause;
import org.epm.project.enums.ProjectStatus;

import java.time.LocalDate;

@Slf4j
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "projects")
@NoArgsConstructor
public class ProjectEntity extends ProjectData<ProjectEntity> implements IEntity {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false)
    private String name;
    @ToString.Exclude
    private String body;
    private LocalDate plannedStartDate;
    private LocalDate plannedEndDate;
    private LocalDate realStartDate;
    private LocalDate realEndDate;
    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer workingHoursCount;
    @Column(nullable = false) @Enumerated(EnumType.STRING)
    private ProjectCause cause;
    private LocalDate materialsReadyDate;
    @Column(nullable = false) @ToString.Exclude
    private String projectLocationUrl;
    @Column(nullable = false) @Enumerated(EnumType.STRING)
    private LocationType locationType;
    @Column(nullable = false) @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    @JsonIgnore
    private boolean isValidEntityVerbose() {
        boolean valid = true;
        if (name != null && name.length() > 5) {
            log.info("Project has valid name: {}", name);
        } else valid = false;
        if (body != null && body.length() > 5) {
            log.info("Project has valid body: {}", body);
        } else valid = false;
        if (arePlannedDatesOk()) {
            log.info("Project has valid planned dates. Start: {}, End: {}", plannedStartDate, plannedEndDate);
        } else valid = false;
        if (areRealDatesOk()) {
            log.info("Project has valid real dates. Start: {}, End: {}", realStartDate, realEndDate);
        } else valid = false;
        if (workingHoursCount != null && workingHoursCount > 0) {
            log.info("Project has valid working hours: {}", workingHoursCount);
        } else valid = false;
        if (cause != null) {
            log.info("Project has valid cause: {}", cause);
        } else valid = false;
        if (materialsReadyDate != null) {
            log.info("Project has valid materials ready: {}", materialsReadyDate);
        } else valid = false;
        if (projectLocationUrl != null) {
            log.info("Project has valid project location: {}", projectLocationUrl);
        } else valid = false;
        if (projectLocationUrl == null || projectLocationUrl.length() > 13) {
            log.info("Project has valid project location URL: {}", projectLocationUrl);
        } else valid = false;
        if (locationType != null) {
            log.info("Project has valid location type: {}", locationType);
        } else valid = false;
        if (isStatusOk()) {
            log.info("Project has valid status: {}", status);
        } else valid = false;
        log.info("Project is valid?: {}", valid);
        return valid;
    }

    @JsonIgnore
    @Override
    public boolean isValidEntity() {
        if (Config.PREFER_VERBOSE_METHODS) return isValidEntityVerbose();
        return name != null && name.length() > 5
            && (body == null || body.length() > 20)
            && (projectLocationUrl == null || projectLocationUrl.length() > 12)
            && (workingHoursCount == null || workingHoursCount > 0)
            && (projectLocationUrl == null || projectLocationUrl.length() > 13)
            && locationType != null && cause != null
            && areDatesOk() && isStatusOk();
    }

}
