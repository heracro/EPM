package org.epm.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.epm.common.model.IDTO;
import org.epm.project.enums.LocationType;
import org.epm.project.enums.ProjectCause;
import org.epm.project.enums.ProjectStatus;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@JsonTypeName("ProjectDTO")
public class ProjectDTO extends ProjectData<ProjectDTO> implements IDTO {
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

    @JsonIgnore
    @Override
    public boolean isValidDTO() {
        return name != null || body != null || plannedStartDate != null
                || plannedEndDate != null || realStartDate != null
                || realEndDate != null || workingHoursCount != null
                || cause != null || materialsReadyDate != null
                || projectLocationUrl != null || locationType != null
                || status != null || action != null;
    }

}
