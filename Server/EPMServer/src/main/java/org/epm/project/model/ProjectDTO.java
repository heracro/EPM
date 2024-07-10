package org.epm.project.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.epm.common.Config;
import org.epm.common.model.IDTO;
import org.epm.common.utils.RandomUtils;

import java.time.LocalDate;
import java.util.Random;

@Data
@NoArgsConstructor
@JsonTypeName("ProjectDTO")
public class ProjectDTO implements IDTO {
    private String name;
    private String body;
    private LocalDate plannedStartDate;
    private LocalDate plannedEndDate;
    private LocalDate realStartDate;
    private LocalDate realEndDate;
    private Integer workingHoursCount;
    private ProjectCause cause;
    private LocalDate materialsReadyDate;
    private String projectLocationUrl;
    private LocationType locationType;
    private ProjectStatus status;
    private String action;

    @Override
    public boolean isValidDTO() {
        return name != null || body != null || plannedStartDate != null
                || plannedEndDate != null || realStartDate != null
                || realEndDate != null || workingHoursCount != null
                || cause != null || materialsReadyDate != null
                || projectLocationUrl != null || locationType != null
                || status != null || action != null;
    }

    public static ProjectDTO randomInstance() {
        ProjectDTO dto = new ProjectDTO();
        Random random = new Random();
        dto.setName("Project " + random.nextInt(1000));
        dto.setBody("This is random project.");
        dto.setPlannedStartDate(RandomUtils.randomDate(Config.BIG_BANG_DATE, LocalDate.now().plusDays(92)));
        dto.setPlannedEndDate(RandomUtils.randomDate(dto.getPlannedStartDate().plusDays(7), Config.FAR_FAR_DATE));
        dto.setStatus(ProjectStatus.randomProjectStatus());
        switch (dto.getStatus()) {
            case COMPLETED:
                dto.setRealEndDate(RandomUtils.randomDate(
                        Config.BIG_BANG_DATE.plusDays(365), LocalDate.now().minusDays(1)));
            case ONGOING:
                dto.setRealStartDate(RandomUtils.randomDate(
                        Config.BIG_BANG_DATE,
                        (dto.getRealEndDate() == null ? LocalDate.now().minusDays(1) : dto.getRealEndDate().minusDays(7))
                ));
        }
        dto.setCause(ProjectCause.randomProjectCause());
        dto.setWorkingHoursCount(dto.getStatus() == ProjectStatus.COMPLETED ? random.nextInt(60) : 0);
        dto.setProjectLocationUrl("file:///home/inventor/some/project/location");
        dto.setLocationType(LocationType.LOCAL);
        return dto;
    }

}
