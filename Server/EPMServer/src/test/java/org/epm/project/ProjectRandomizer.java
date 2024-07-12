package org.epm.project;

import lombok.extern.slf4j.Slf4j;
import org.epm.common.configuration.Config;
import org.epm.common.utils.RandomUtils;
import org.epm.project.enums.LocationType;
import org.epm.project.enums.ProjectCause;
import org.epm.project.enums.ProjectStatus;
import org.epm.project.model.ProjectDTO;
import org.epm.project.model.ProjectData;
import org.epm.project.model.ProjectEntity;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Random;

@Slf4j
public class ProjectRandomizer {

    private static final int maxTries = 40;

    public ProjectDTO provideEmptyDTO() {
        return new ProjectDTO();
    }

    public ProjectEntity provideValidEntity() {
        return (ProjectEntity) randomizeData(new ProjectEntity());
    }

    private ProjectData<?> randomizeData(ProjectData<?> data) {
        try {
            setRandomName(data);
            setRandomBody(data);
            setRandomPlannedStartDate(data);
            setRandomPlannedEndDate(data);
            setRandomRealStartDate(data);
            setRandomRealEndDate(data);
            setRandomWorkHoursCount(data);
            setRandomCause(data);
            setRandomMaterialsReadyDate(data);
            setRandomProjectLocationUrl(data);
            setRandomLocationType(data);
            setRandomStatus(data);
        } catch (RuntimeException e) {
            // This means there's a clash in dates, and there's little to no chance for setting something that fits.
            log.warn("Failed to randomize data, trying again. ({} ...)", e.getMessage().substring(0,255));
            randomizeData(data);
        }
        return data;
    }

    private void setRandomName(ProjectData<?> data) {
        String name = data.getName();
        do {
            data.setName("Project " + RandomUtils.randomInt(2000));
            log.info("set random name: {}", data.getName());
        } while (name != null && name.equals(data.getName()));
    }

    private void setRandomBody(ProjectData<?> data) {
        String body =data.getBody();
        do {
           data.setBody("Random body " + RandomUtils.randomInt(2000));
            log.info("set random body: {}",data.getBody());
        } while (body != null && body.equals(data.getBody()));
    }

    private void setRandomPlannedStartDate(ProjectData<?> data) {
        LocalDate plannedStartDate =data.getPlannedStartDate();
        int wrongHitCounter = 0;
        do {
            if (++wrongHitCounter > maxTries) {
                throw new RuntimeException("Failed to hit right planned start date. Object: " + this);
            }
           data.setPlannedStartDate(RandomUtils
                    .randomDate(Config.BIG_BANG_DATE, LocalDate.now().plusDays(92)));
            log.info("set random planned start date: {}",data.getPlannedStartDate());
        } while (plannedStartDate != null && plannedStartDate.equals(data.getPlannedStartDate())
                || !data.areDatesOk());
    }

    private void setRandomPlannedEndDate(ProjectData<?> data) {
        LocalDate plannedEndDate =data.getPlannedEndDate();
        if (data.getPlannedStartDate() == null) {
            data.setPlannedStartDate(LocalDate.now().minusDays(180));
        }
        int wrongHitCounter = 0;
        do {
            if (++wrongHitCounter > maxTries) {
                throw new RuntimeException("Failed to hit right planned end date. Object: " + this);
            }
           data.setPlannedEndDate(RandomUtils
                    .randomDate(data.getPlannedStartDate().plusDays(7), Config.FAR_FAR_DATE));
            log.info("set random planned end date: {}",data.getPlannedEndDate());
        } while (plannedEndDate != null && plannedEndDate.equals(data.getPlannedEndDate())
                || !data.areDatesOk());
    }

    private void setRandomRealStartDate(ProjectData<?> data) {
        LocalDate realStartDate =data.getRealStartDate();
        int wrongHitCounter = 0;
        do {
            if (++wrongHitCounter > maxTries) {
                throw new RuntimeException("Failed to hit right real start date. Object: " + this);
            }
           data.setRealStartDate(RandomUtils
                    .randomDate(LocalDate.now().minusDays(180), LocalDate.now()).minusDays(90));
            log.info("set random real start date: {}",data.getRealStartDate());
        } while (realStartDate != null && realStartDate.equals(data.getRealStartDate())
                || !data.areDatesOk());
    }

    private void setRandomRealEndDate(ProjectData<?> data) {
        LocalDate realEndDate = data.getRealEndDate();
        LocalDate rs = (data.getPlannedStartDate() == null
                || data.getPlannedStartDate().isAfter(LocalDate.now().minusDays(7))
                ? LocalDate.now().minusDays(365) : data.getPlannedStartDate());
        int wrongHitCounter = 0;
        do {
            if (++wrongHitCounter > maxTries) {
                throw new RuntimeException("Failed to hit right real end date. Object: " + this);
            }
           data.setRealEndDate(RandomUtils
                    .randomDate(rs, LocalDate.now()));
            log.info("set random real end date: {}", data.getRealEndDate());
        } while (data.getRealStartDate() != null
                && (realEndDate != null && realEndDate.equals(data.getRealEndDate())
                || !data.areDatesOk()));
    }

    private void setRandomWorkHoursCount(ProjectData<?> data) {
        Integer workingHoursCount =data.getWorkingHoursCount();
        do {
           data.setWorkingHoursCount(RandomUtils.randomInt(60));
            log.info("set random work hours count: {}",data.getWorkingHoursCount());
        } while (workingHoursCount != null && !workingHoursCount.equals(data.getWorkingHoursCount()));
    }

    private void setRandomCause(ProjectData<?> data) {
        ProjectCause cause =data.getCause();
        do {
           data.setCause(randomProjectCause());
            log.info("set random cause: {}",data.getCause());
        } while (cause != null && !Objects.equals(cause,data.getCause()));
    }

    private static ProjectCause randomProjectCause() {
        Random random = new Random();
        return ProjectCause.values()[random.nextInt(ProjectCause.values().length)];
    }

    private void setRandomMaterialsReadyDate(ProjectData<?> data) {
        LocalDate materialsReadyDate =data.getMaterialsReadyDate();
        int wrongHitCounter = 0;
        do {
            if (++wrongHitCounter > maxTries) {
                throw new RuntimeException("Failed to hit right materials ready date. Object: " + this);
            }
           data.setMaterialsReadyDate(RandomUtils
                    .randomDate(Config.BIG_BANG_DATE, LocalDate.now().plusDays(14)));
            log.info("set random materials ready date: {}",data.getMaterialsReadyDate());
        } while (materialsReadyDate != null && materialsReadyDate.equals(data.getMaterialsReadyDate())
                || !data.areDatesOk());
    }

    private void setRandomProjectLocationUrl(ProjectData<?> data) {
        String projectLocationUrl =data.getProjectLocationUrl();
        do {
           data.setProjectLocationUrl("https://www.myprojects.com/" + RandomUtils.randomInt(2000));
            log.info("set random project location url: {}",data.getProjectLocationUrl());
        } while (projectLocationUrl != null && projectLocationUrl.equals(data.getProjectLocationUrl()));
    }

    private void setRandomLocationType(ProjectData<?> data) {
        LocationType locationType =data.getLocationType();
        do {
           data.setLocationType(randomLocationType());
            log.info("set random location type: {}",data.getLocationType());
        } while (locationType != null && locationType.equals(data.getLocationType()));
    }

    private static LocationType randomLocationType() {
        Random random = new Random();
        return LocationType.values()[random.nextInt(LocationType.values().length)];
    }

    private void setRandomStatus(ProjectData<?> data) {
        do {
           data.setStatus(randomProjectStatus());
            log.info("set random status: {}",data.getStatus());
        } while (!data.isStatusOk());
    }

    private static ProjectStatus randomProjectStatus() {
        Random random = new Random();
        return ProjectStatus.values()[random.nextInt(ProjectStatus.values().length)];
    }

}
