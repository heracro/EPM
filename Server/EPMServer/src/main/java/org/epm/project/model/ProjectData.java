package org.epm.project.model;

import lombok.extern.slf4j.Slf4j;
import org.epm.common.model.DataModel;
import org.epm.project.enums.LocationType;
import org.epm.project.enums.ProjectCause;
import org.epm.project.enums.ProjectStatus;

import java.time.LocalDate;

@Slf4j
public abstract class ProjectData<T extends ProjectData<T>> implements DataModel {

    @Override
    public boolean isValidEntity() {
        return getName() != null && getName().length() > 5
                && (getBody() == null || getBody().length() > 20)
                && (getProjectLocationUrl() == null || getProjectLocationUrl().length() > 13)
                && (getWorkingHoursCount() == null || getWorkingHoursCount() > 0)
                && getLocationType() != null && getCause() != null && getStatus() != null
                && areDatesOk() && isStatusOk();
    }

    public boolean areDatesOk() {
        return arePlannedDatesOk() && areRealDatesOk() && isMaterialsReadyDateOk();
    }

    protected boolean arePlannedDatesOk() {
        return getPlannedEndDate() == null
                || (getPlannedStartDate() != null && getPlannedStartDate().isBefore(getPlannedEndDate()));
    }

    protected boolean areRealDatesOk() {
        return getRealEndDate() == null
                || (getRealStartDate() != null && getRealStartDate().isBefore(getRealEndDate()));
    }

    protected boolean isMaterialsReadyDateOk() {
        return getMaterialsReadyDate() == null || getRealStartDate() == null
                || getMaterialsReadyDate().isBefore(getRealStartDate());
    }

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

    public abstract void setName(String name);
    public abstract String getName();
    public abstract void setBody(String body);
    public abstract String getBody();
    public abstract void setPlannedStartDate(LocalDate plannedStartDate);
    public abstract LocalDate getPlannedStartDate();
    public abstract void setPlannedEndDate(LocalDate plannedEndDate);
    public abstract LocalDate getPlannedEndDate();
    public abstract void setRealStartDate(LocalDate realStartDate);
    public abstract LocalDate getRealStartDate();
    public abstract void setRealEndDate(LocalDate realEndDate);
    public abstract LocalDate getRealEndDate();
    public abstract void setStatus(ProjectStatus status);
    public abstract ProjectStatus getStatus();
    public abstract void setCause(ProjectCause cause);
    public abstract ProjectCause getCause();
    public abstract void setWorkingHoursCount(Integer workingHoursCount);
    public abstract Integer getWorkingHoursCount();
    public abstract void setProjectLocationUrl(String projectLocationUrl);
    public abstract String getProjectLocationUrl();
    public abstract void setLocationType(LocationType locationType);
    public abstract LocationType getLocationType();
    public abstract void setMaterialsReadyDate(LocalDate materialsReadyDate);
    public abstract LocalDate getMaterialsReadyDate();

}
