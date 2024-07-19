package org.epm.project;

import org.epm.EntityNotEligibleForUpdateException;
import org.epm.common.configuration.Config;
import org.epm.common.utils.RandomUtils;
import org.epm.project.enums.LocationType;
import org.epm.project.enums.ProjectCause;
import org.epm.project.enums.ProjectStatus;
import org.epm.project.model.ProjectEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import static org.epm.project.enums.ProjectStatus.*;

public class ProjectRandomizer {

    private static final int GENERATION_TRIALS_COUNT = 500;

    public ProjectEntity setRandomValidStatusAndDates(ProjectEntity project) {
        int trials = GENERATION_TRIALS_COUNT;
        boolean success = false;
        while (!success && trials > 0) {
            try {
                project.setStatus(randomProjectStatus());
                project.setPlannedStartDate(getRandomInvalidPlannedStartDate(project));
                project.setPlannedEndDate(getRandomInvalidPlannedEndDate(project));
                success = true;
            } catch (Exception e) {

            }
        }
        return project;
    }

    public String getRandomValidName(ProjectEntity project) {
        String name;
        do {
            name = "Project " + (RandomUtils.randomInt(2000) + 99);
        } while (project.getName() != null && name.equals(project.getName()));
        return name;
    }
    public String getRandomInvalidName(ProjectEntity project) {
        String name;
        do {
            name = "P." + RandomUtils.randomInt(100);
        } while (project.getName() != null && name.equals(project.getName()));
        return name;
    }

    public String getRandomValidBody(ProjectEntity project) {
        String body;
        do {
           body = "This is project's random body " + RandomUtils.randomInt(2000) + ". And that's all, folks!";
        } while (project.getBody() != null && body.equals(project.getBody()));
        return body;
    }
    public String getRandomInvalidBody(ProjectEntity project) {
        String body;
        do {
            body = "Too short body " + RandomUtils.randomInt(100);
        } while (project.getBody() != null && body.equals(project.getBody()));
        return body;
    }

    public LocalDate getRandomValidPlannedStartDate(ProjectEntity project) {
        return RandomUtils.randomDate(LocalDate.now(), Config.FAR_FAR_DATE);
    }
    public LocalDate getRandomInvalidPlannedStartDate(ProjectEntity project) {
        return RandomUtils.randomDate(Config.BIG_BANG_DATE, LocalDate.now());
    }

    public LocalDate getRandomValidPlannedEndDate(ProjectEntity project) throws EntityNotEligibleForUpdateException {
        if (List.of(PLANNED, AWAITING_MATERIALS, READY).contains(project.getStatus())
                || project.getPlannedStartDate() == null) {
            throw new EntityNotEligibleForUpdateException("Not possible to set valid PlannedEndDate in " + project);
        } else {
            return RandomUtils.randomDate(project.getPlannedStartDate().plusDays(1), Config.FAR_FAR_DATE);
        }
    }
    public LocalDate getRandomInvalidPlannedEndDate(ProjectEntity project) {
        return RandomUtils.randomDate(Config.BIG_BANG_DATE,
                project.getPlannedStartDate() != null ? project.getPlannedStartDate() : Config.FAR_FAR_DATE);
    }

    public void setRandomValidRealStartDate(ProjectEntity project) {}
    public void setRandomInvalidRealStartDate(ProjectEntity project) {}

    public void setRandomValidRealEndDate(ProjectEntity project) {}
    public void setRandomInvalidRealEndDate(ProjectEntity project) {}

    public void setRandomValidMaterialsReadyDate(ProjectEntity project) {}
    public void setRandomInvalidMaterialsReadyDate(ProjectEntity project) {}

    public void setRandomValidWorkHoursCount(ProjectEntity project) {
        Integer workingHoursCount =project.getWorkingHoursCount();
        do {
           project.setWorkingHoursCount(RandomUtils.randomInt(60));
        } while (workingHoursCount != null && !workingHoursCount.equals(project.getWorkingHoursCount()));
    }
    public void setRandomInvalidWorkHoursCount(ProjectEntity project) {}

    public void setRandomValidWorkHoursPlanned(ProjectEntity project) {
        Integer workingHoursCount =project.getWorkingHoursPlanned();
        do {
            project.setWorkingHoursPlanned(RandomUtils.randomInt(60));
        } while (workingHoursCount != null && !workingHoursCount.equals(project.getWorkingHoursPlanned()));
    }
    public void setRandomInvalidWorkHoursPlanned(ProjectEntity project) {}

    public void setRandomValidProjectLocationUrl(ProjectEntity project) {
        String projectLocationUrl = project.getProjectLocation();
        do {
            project.setProjectLocation("https://www.myprojects.com/" + RandomUtils.randomInt(2000));
        } while (projectLocationUrl != null && projectLocationUrl.equals(project.getProjectLocation()));
    }
    public void setRandomInvalidProjectLocationUrl(ProjectEntity project) {}

    public void setRandomValidLocationType(ProjectEntity project) {
        LocationType locationType = project.getLocationType();
        do {
            project.setLocationType(randomLocationType());
        } while (locationType != null && locationType.equals(project.getLocationType()));
    }
    public void setRandomValidStatus(ProjectEntity project) {
        do {
            project.setStatus(randomProjectStatus());
        } while (!project.isStatusOk());
    }
    public void setRandomValidCause(ProjectEntity project) {
    }

    private static ProjectCause randomProjectCause() {
        return ProjectCause.values()[new Random().nextInt(ProjectCause.values().length)];
    }
    private static LocationType randomLocationType() {
        return LocationType.values()[new Random().nextInt(LocationType.values().length)];
    }
    private static ProjectStatus randomProjectStatus() {
        return ProjectStatus.values()[new Random().nextInt(ProjectStatus.values().length)];
    }
    public static ProjectStatus randomProjectStatus(List<ProjectStatus> list) {
        return list.get(new Random().nextInt(list.size()));
    }
}
