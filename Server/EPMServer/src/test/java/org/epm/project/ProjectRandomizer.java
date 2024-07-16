package org.epm.project;

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

public class ProjectRandomizer {

    private static final int maxTries = 40;

    public ProjectDTO provideEmptyDTO() {
        return new ProjectDTO();
    }

    public ProjectEntity provideValidEntity() {
        return (ProjectEntity) randomizeData(new ProjectEntity());
    }

    public ProjectData randomizeData(ProjectData data) {
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
            randomizeData(data);
        }
        return data;
    }

    public void setRandomName(ProjectData data) {
        String name = data.getName();
        do {
            data.setName("Project " + RandomUtils.randomInt(2000));
        } while (name != null && name.equals(data.getName()));
    }

    public void setRandomBody(ProjectData data) {
        String body =data.getBody();
        do {
           data.setBody("Random body " + RandomUtils.randomInt(2000));
        } while (body != null && body.equals(data.getBody()));
    }

    public void setRandomPlannedStartDate(ProjectData data) {
        LocalDate plannedStartDate =data.getPlannedStartDate();
        int wrongHitCounter = 0;
        do {
            if (++wrongHitCounter > maxTries) {
                throw new RuntimeException("Failed to hit right planned start date. Object: " + this);
            }
           data.setPlannedStartDate(RandomUtils
                    .randomDate(Config.BIG_BANG_DATE, LocalDate.now().plusDays(92)));
        } while (plannedStartDate != null && plannedStartDate.equals(data.getPlannedStartDate())
            /*|| !data.areDatesOk()*/);
    }

    public void setRandomPlannedEndDate(ProjectData data) {
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
        } while (plannedEndDate != null && plannedEndDate.equals(data.getPlannedEndDate())
            /*|| !data.areDatesOk()*/);
    }

    public void setRandomRealStartDate(ProjectData data) {
        LocalDate realStartDate =data.getRealStartDate();
        int wrongHitCounter = 0;
        do {
            if (++wrongHitCounter > maxTries) {
                throw new RuntimeException("Failed to hit right real start date. Object: " + this);
            }
           data.setRealStartDate(RandomUtils
                    .randomDate(LocalDate.now().minusDays(180), LocalDate.now()).minusDays(90));
        } while (realStartDate != null && realStartDate.equals(data.getRealStartDate())
            /*|| !data.areDatesOk()*/);
    }

    public void setRandomRealEndDate(ProjectData data) {
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
        } while (data.getRealStartDate() != null
                && (realEndDate != null && realEndDate.equals(data.getRealEndDate())
                /*|| !data.areDatesOk()*/));
    }

    public void setRandomWorkHoursCount(ProjectData data) {
        Integer workingHoursCount =data.getWorkingHoursCount();
        do {
           data.setWorkingHoursCount(RandomUtils.randomInt(60));
        } while (workingHoursCount != null && !workingHoursCount.equals(data.getWorkingHoursCount()));
    }

    public void setRandomCause(ProjectData data) {
        ProjectCause cause =data.getCause();
        do {
           data.setCause(randomProjectCause());
        } while (cause != null && !Objects.equals(cause,data.getCause()));
    }

    public static ProjectCause randomProjectCause() {
        Random random = new Random();
        return ProjectCause.values()[random.nextInt(ProjectCause.values().length)];
    }

    public void setRandomMaterialsReadyDate(ProjectData data) {
        LocalDate materialsReadyDate = data.getMaterialsReadyDate();
        int wrongHitCounter = 0;
        do {
            if (++wrongHitCounter > maxTries) {
                throw new RuntimeException("Failed to hit right materials ready date. Object: " + this);
            }
           data.setMaterialsReadyDate(RandomUtils
                    .randomDate(Config.BIG_BANG_DATE, LocalDate.now().plusDays(14)));
        } while (materialsReadyDate != null && materialsReadyDate.equals(data.getMaterialsReadyDate())
                /*|| !data.areDatesOk()*/);
    }

    public void setRandomProjectLocationUrl(ProjectData data) {
        String projectLocationUrl = data.getProjectLocation();
        do {
           data.setProjectLocation("https://www.myprojects.com/" + RandomUtils.randomInt(2000));
        } while (projectLocationUrl != null && projectLocationUrl.equals(data.getProjectLocation()));
    }

    public void setRandomLocationType(ProjectData data) {
        LocationType locationType = data.getLocationType();
        do {
           data.setLocationType(randomLocationType());
        } while (locationType != null && locationType.equals(data.getLocationType()));
    }

    public static LocationType randomLocationType() {
        Random random = new Random();
        return LocationType.values()[random.nextInt(LocationType.values().length)];
    }

    public void setRandomStatus(ProjectData data) {
        do {
           data.setStatus(randomProjectStatus());
        } while (!data.isStatusOk());
    }

    public static ProjectStatus randomProjectStatus() {
        Random random = new Random();
        return ProjectStatus.values()[random.nextInt(ProjectStatus.values().length)];
    }

}
