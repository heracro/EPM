package org.epm.project.model;

import lombok.extern.slf4j.Slf4j;
import org.epm.common.Config;
import org.epm.common.utils.RandomUtils;
import org.epm.project.model.enums.LocationType;
import org.epm.project.model.enums.ProjectCause;
import org.epm.project.model.enums.ProjectStatus;

import java.time.LocalDate;
import java.util.Objects;

@Slf4j
public abstract class ProjectData<T extends ProjectData<T>> {

    public T randomizeProjectData() {
        return self().setRandomName()
                .setRandomBody()
                .setRandomPlannedStartDate()
                .setRandomPlannedEndDate()
                .setRandomRealStartDate()
                .setRandomRealEndDate()
                .setRandomWorkHoursCount()
                .setRandomCause()
                .setRandomMaterialsReadyDate()
                .setRandomProjectLocationUrl()
                .setRandomLocationType()
                .setRandomStatus();
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

    public T setRandomName() {
        String name = getName();
        do {
            setName("Project " + RandomUtils.randomInt(2000));
            log.info("set random name: {}", getName());
        } while (name != null && name.equals(getName()));
        return self();
    }

    public T setRandomBody() {
        String body = getBody();
        do {
            setBody("Random body " + RandomUtils.randomInt(2000));
            log.info("set random body: {}", getBody());
        } while (body != null && body.equals(getBody()));
        return self();
    }

    public T setRandomPlannedStartDate() {
        LocalDate plannedStartDate = getPlannedStartDate();
        int wrongHitCounter = 0;
        do {
            if (++wrongHitCounter > 400) throw new RuntimeException("Failed to hit right planned start date. Object: " + this);
            setPlannedStartDate(RandomUtils
                    .randomDate(Config.BIG_BANG_DATE, LocalDate.now().plusDays(92)));
            log.info("set random planned start date: {}", getPlannedStartDate());
        } while (plannedStartDate != null && plannedStartDate.equals(getPlannedStartDate()) || !arePlannedDatesOk());
        return self();
    }

    public T setRandomPlannedEndDate() {
        LocalDate plannedEndDate = getPlannedEndDate();
        if (getPlannedStartDate() == null) setPlannedStartDate(LocalDate.now().minusDays(180));
        int wrongHitCounter = 0;
        do {
            if (++wrongHitCounter > 400) throw new RuntimeException("Failed to hit right planned end date. Object: " + this);
            setPlannedEndDate(RandomUtils
                    .randomDate(getPlannedStartDate().plusDays(7), Config.FAR_FAR_DATE));
            log.info("set random planned end date: {}", getPlannedEndDate());
        } while (plannedEndDate != null && plannedEndDate.equals(getPlannedEndDate()) || !arePlannedDatesOk());
        return self();
    }

    public T setRandomRealStartDate() {
        LocalDate realStartDate = getRealStartDate();
        int wrongHitCounter = 0;
        do {
            if (++wrongHitCounter > 400) throw new RuntimeException("Failed to hit right real start date. Object: " + this);
            setRealStartDate(RandomUtils
                    .randomDate(LocalDate.now().minusDays(180), LocalDate.now()).minusDays(90));
            log.info("set random real start date: {}", getRealStartDate());
        } while (realStartDate != null && realStartDate.equals(getRealStartDate()) || !areRealDatesOk());
        return self();
    }

    public T setRandomRealEndDate() {
        LocalDate realEndDate = getRealEndDate();
        LocalDate rs = (getPlannedStartDate() == null || getPlannedStartDate().isAfter(LocalDate.now().minusDays(7))
                ? LocalDate.now().minusDays(365) : getPlannedStartDate());
        int wrongHitCounter = 0;
        do {
            if (++wrongHitCounter > 400) throw new RuntimeException("Failed to hit right real end date. Object: " + this);
            setRealEndDate(RandomUtils
                    .randomDate(rs, LocalDate.now()));
            log.info("set random real end date: {}", getRealEndDate());
        } while (getRealStartDate() != null && (realEndDate != null && realEndDate.equals(getRealEndDate()) || !areRealDatesOk()));
        return self();
    }

    public T setRandomWorkHoursCount() {
        Integer workingHoursCount = getWorkingHoursCount();
        do {
            setWorkingHoursCount(RandomUtils.randomInt(60));
            log.info("set random work hours count: {}", getWorkingHoursCount());
        } while (workingHoursCount != null && !workingHoursCount.equals(getWorkingHoursCount()));
        return self();
    }

    public T setRandomCause() {
        ProjectCause cause = getCause();
        do {
            setCause(ProjectCause.randomProjectCause());
            log.info("set random cause: {}", getCause());
        } while (cause != null && !Objects.equals(cause, getCause()));
        return self();
    }

    public T setRandomMaterialsReadyDate() {
        LocalDate materialsReadyDate = getMaterialsReadyDate();
        int wrongHitCounter = 0;
        do {
            if (++wrongHitCounter > 400) throw new RuntimeException("Failed to hit right materials ready date. Object: " + this);
            setMaterialsReadyDate(RandomUtils
                    .randomDate(Config.BIG_BANG_DATE, LocalDate.now().plusDays(14)));
            log.info("set random materials ready date: {}", getMaterialsReadyDate());
        } while (materialsReadyDate != null && materialsReadyDate.equals(getMaterialsReadyDate()) || !isMaterialsReadyDateOk());
        return self();
    }

    public T setRandomProjectLocationUrl() {
        String projectLocationUrl = getProjectLocationUrl();
        do {
            setProjectLocationUrl("https://www.myprojects.com/" + RandomUtils.randomInt(2000));
            log.info("set random project location url: {}", getProjectLocationUrl());
        } while (projectLocationUrl != null && projectLocationUrl.equals(getProjectLocationUrl()));
        return self();
    }

    public T setRandomLocationType() {
        LocationType locationType = getLocationType();
        do {
            setLocationType(LocationType.randomLocationType());
            log.info("set random location type: {}", getLocationType());
        } while (locationType != null && locationType.equals(getLocationType()));
        return self();
    }

    public T setRandomStatus() {
        do {
            setStatus(ProjectStatus.randomProjectStatus());
            log.info("set random status: {}", getStatus());
        } while (!isStatusOk());
        return self();
    }

    protected abstract T self();
    protected abstract void setName(String name);
    protected abstract String getName();
    protected abstract void setBody(String body);
    protected abstract String getBody();
    protected abstract void setPlannedStartDate(LocalDate plannedStartDate);
    protected abstract LocalDate getPlannedStartDate();
    protected abstract void setPlannedEndDate(LocalDate plannedEndDate);
    protected abstract LocalDate getPlannedEndDate();
    protected abstract void setRealStartDate(LocalDate realStartDate);
    protected abstract LocalDate getRealStartDate();
    protected abstract void setRealEndDate(LocalDate realEndDate);
    protected abstract LocalDate getRealEndDate();
    protected abstract void setStatus(ProjectStatus status);
    protected abstract ProjectStatus getStatus();
    protected abstract void setCause(ProjectCause cause);
    protected abstract ProjectCause getCause();
    protected abstract void setWorkingHoursCount(Integer workingHoursCount);
    protected abstract Integer getWorkingHoursCount();
    protected abstract void setProjectLocationUrl(String projectLocationUrl);
    protected abstract String getProjectLocationUrl();
    protected abstract void setLocationType(LocationType locationType);
    protected abstract LocationType getLocationType();
    protected abstract void setMaterialsReadyDate(LocalDate materialsReadyDate);
    protected abstract LocalDate getMaterialsReadyDate();

}
