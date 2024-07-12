package org.epm.project.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.epm.bom.model.BomEntity;
import org.epm.common.model.DataModel;
import org.epm.project.enums.LocationType;
import org.epm.project.enums.ProjectCause;
import org.epm.project.enums.ProjectStatus;
import org.epm.tag.model.TagEntity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public abstract class ProjectData implements DataModel {

    @Column(nullable = false, unique = true)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    @ToString.Exclude
    private String body;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate plannedStartDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate plannedEndDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate realStartDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate realEndDate;

    @Column(nullable = false)
    private Integer workingHoursCount;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProjectCause cause;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate materialsReadyDate;

    @Column(nullable = false)
    private String projectLocationUrl;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LocationType locationType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    @JsonIgnore
    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BomEntity> boms;

    @ManyToMany
    @JoinTable(name = "project_tag", joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<TagEntity> tags = new HashSet<>();

    @JsonIgnore
    @Override
    public boolean isValidEntity() {
        return getName() != null && getName().length() > 5
                && (getBody() == null || getBody().length() > 20)
                && (getProjectLocationUrl() == null || getProjectLocationUrl().length() > 13)
                && (getWorkingHoursCount() == null || getWorkingHoursCount() > 0)
                && getLocationType() != null && getCause() != null && getStatus() != null
                && areDatesOk() && isStatusOk();
    }

    @JsonIgnore
    @Override
    public boolean isValidDTO() {
        return getName() != null || getBody() != null || getPlannedStartDate() != null
                || getPlannedEndDate() != null || getRealStartDate() != null
                || getRealEndDate() != null || getWorkingHoursCount() != null
                || getCause() != null || getMaterialsReadyDate() != null
                || getProjectLocationUrl() != null || getLocationType() != null
                || getStatus() != null;
    }

    private boolean areDatesOk() {
        return arePlannedDatesOk() && areRealDatesOk() && isMaterialsReadyDateOk();
    }

    private boolean arePlannedDatesOk() {
        return getPlannedEndDate() == null
                || (getPlannedStartDate() != null && getPlannedStartDate().isBefore(getPlannedEndDate()));
    }

    private boolean areRealDatesOk() {
        return getRealEndDate() == null
                || (getRealStartDate() != null && getRealStartDate().isBefore(getRealEndDate()));
    }

    private boolean isMaterialsReadyDateOk() {
        return getMaterialsReadyDate() == null || getRealStartDate() == null
                || getMaterialsReadyDate().isBefore(getRealStartDate());
    }

    @JsonIgnore
    public boolean isStatusOk() {
        if (getStatus() == null) return false;
        switch (getStatus()) {
            case PLANNED -> {
                return getRealStartDate() == null && getRealEndDate() == null && getMaterialsReadyDate() == null;
            }
            case AWAITING_MATERIALS -> {
                return getRealStartDate() == null && getRealEndDate() == null && getMaterialsReadyDate() != null
                        && getMaterialsReadyDate().isAfter(LocalDate.now().minusDays(1));
            }
            case READY -> {
                return getRealStartDate() == null && getRealEndDate() == null && getMaterialsReadyDate() != null
                        && getMaterialsReadyDate().isBefore(LocalDate.now().plusDays(1));
            }
            case ONGOING -> {
                return getRealStartDate() != null && getRealEndDate() == null && getMaterialsReadyDate() != null
                        && getMaterialsReadyDate().isBefore(LocalDate.now().plusDays(1));
            }
            case COMPLETED -> {
                return getRealStartDate() != null && getRealEndDate() != null
                        && getRealEndDate().isAfter(getRealStartDate()) && getMaterialsReadyDate() != null
                        && getMaterialsReadyDate().isBefore(LocalDate.now().plusDays(1));
            }
        }
        return false;
    }

    public String toString() {
        return "Project {" + getId() + ", " + getName() + ", "
                + getCause() + ", " + getStatus() + "}";
    }
}
