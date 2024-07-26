package hihi.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import hihi.dto.enums.LocationType;
import hihi.dto.enums.ProjectCause;
import hihi.dto.enums.ProjectStatus;
import hihi.gui.layout.content.project.Project;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@ToString(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
//@JsonTypeName("Project")
public class ProjectDto extends AbstractDto {

    private String name;
    private String body;
    private LocalDate plannedStartDate;
    private LocalDate plannedEndDate;
    private LocalDate realStartDate;
    private LocalDate realEndDate;
    private LocalDate materialsReadyDate;
    private Integer workingHoursCount;
    private Integer workingHoursPlanned;
    private String projectLocation;
    private LocationType locationType;
    private ProjectStatus status;
    private ProjectCause cause;
    private Set<TagDto> tags;

    public ProjectDto(Project project) {
        name = project.getName().getValue();
        body = project.getBody().getValue();
        plannedStartDate = project.getPlannedStartDate().getValue();
        plannedEndDate = project.getPlannedEndDate().getValue();
        realStartDate = project.getRealStartDate().getValue();
        realEndDate = project.getRealEndDate().getValue();
        materialsReadyDate = project.getMaterialsReadyDate().getValue();
        workingHoursCount = project.getWorkingHoursCount().getValue();
        workingHoursPlanned = project.getWorkingHoursPlanned().getValue();
        projectLocation = project.getProjectLocation().getValue();
        locationType = project.getLocationType().getValue();
        status = project.getStatus().getValue();
        cause = project.getCause().getValue();
        if (project.getTags().get() != null) {
            tags = project.getTags().get().stream().map(TagDto::new).collect(Collectors.toSet());
        }
    }

}
