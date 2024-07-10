package org.epm.project.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.epm.common.Config;
import org.epm.common.model.IEntity;
import org.epm.common.utils.RandomUtils;

import java.time.LocalDate;
import java.util.Random;

@Data
@Entity
@NoArgsConstructor
public class ProjectEntity implements IEntity {
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

    @Override
    public boolean isValidEntity() {
        return name != null && name.length() > 5
                && (body == null || body.length() > 20)
                && cause != null && projectLocationUrl != null
                && locationType != null && status != null;
    }

    public static ProjectEntity randomInstance() {
        ProjectEntity dto = new ProjectEntity();
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
