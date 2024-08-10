package hihi.content.project;

import com.fasterxml.jackson.annotation.JsonTypeName;
import hihi.content.bom.BomDto;
import hihi.content.common.dataModel.AbstractDto;
import hihi.content.enums.LocationType;
import hihi.content.enums.ProjectStatus;
import hihi.content.tag.TagDto;
import hihi.content.enums.ProjectCause;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@JsonTypeName("Project")
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
    private List<BomDto> boms;
    private Set<TagDto> tags;

    public ProjectDto(Project project) {
        name = project.nameProperty().getValue();
        body = project.bodyProperty().getValue();
        plannedStartDate = project.plannedStartDateProperty().getValue();
        plannedEndDate = project.plannedEndDateProperty().getValue();
        realStartDate = project.realStartDateProperty().getValue();
        realEndDate = project.realEndDateProperty().getValue();
        materialsReadyDate = project.materialsReadyDateProperty().getValue();
        workingHoursCount = project.workingHoursCountProperty().getValue();
        workingHoursPlanned = project.workingHoursPlannedProperty().getValue();
        projectLocation = project.projectLocationProperty().getValue();
        locationType = project.locationTypeProperty().getValue();
        status = project.statusProperty().getValue();
        cause = project.causeProperty().getValue();
        if (project.tagsProperty().get() != null) {
            tags = project.tagsProperty().get().stream().map(TagDto::new).collect(Collectors.toSet());
        }
    }

}
