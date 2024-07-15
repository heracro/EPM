package org.epm.project.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.epm.bom.model.BomData;
import org.epm.common.model.AbstractModuleData;
import org.epm.project.enums.LocationType;
import org.epm.project.enums.ProjectCause;
import org.epm.project.enums.ProjectStatus;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@MappedSuperclass
public abstract class ProjectData extends AbstractModuleData {

    @Column(nullable = false, unique = true)
    private Integer uid;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
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

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProjectCause cause;

    @JsonIgnore
    public boolean isValidEntity() {
        return getName() != null && getName().length() > 5
                && (getBody() == null || getBody().length() > 20)
                && (getProjectLocationUrl() == null || getProjectLocationUrl().length() > 13)
                && (getWorkingHoursCount() == null || getWorkingHoursCount() > 0)
                && getLocationType() != null && getCause() != null && getStatus() != null
                && areDatesOk() && isStatusOk();
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
        return "\n\tProject {"
                + "\n\t\tuid: " + getUid()
                + ",\n\t\tid: " + getId()
                + ",\n\t\tname: " + getName()
                + ",\n\t\tplannedStartDate: " + getPlannedStartDate()
                + ",\n\t\tplannedEndDate: " + getPlannedEndDate()
                + ",\n\t\trealStartDate: " + getRealStartDate()
                + ",\n\t\trealEndDate: " + getRealEndDate()
                + ",\n\t\tworkingHoursCount: " + getWorkingHoursCount()
                + ",\n\t\tmaterialsReadyDate: " + getMaterialsReadyDate()
                + ",\n\t\tprojectLocationUrl: " + getProjectLocationUrl()
                + ",\n\t\tlocationType: " + getLocationType()
                + ",\n\t\tstatus: " + getStatus()
                + ",\n\t\tcause: " + getCause()
                + "\n\t}";
    }

    public abstract List<? extends BomData> getBoms();
}
