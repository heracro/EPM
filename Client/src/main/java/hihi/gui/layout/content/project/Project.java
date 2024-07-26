package hihi.gui.layout.content.project;

import hihi.dto.ProjectDto;
import hihi.dto.enums.LocationType;
import hihi.dto.enums.ProjectCause;
import hihi.dto.enums.ProjectStatus;
import hihi.gui.layout.content.tag.Tag;
import javafx.beans.property.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class Project {
    private final BooleanProperty selected = new SimpleBooleanProperty(false);
    private final IntegerProperty uid = new SimpleIntegerProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty body = new SimpleStringProperty();
    private final ObjectProperty<LocalDate> plannedStartDate = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDate> plannedEndDate = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDate> realStartDate = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDate> realEndDate = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDate> materialsReadyDate = new SimpleObjectProperty<>();
    private final IntegerProperty workingHoursCount = new SimpleIntegerProperty();
    private final IntegerProperty workingHoursPlanned = new SimpleIntegerProperty();
    private final StringProperty projectLocation = new SimpleStringProperty();
    private final ObjectProperty<LocationType> locationType = new SimpleObjectProperty<>();
    private final ObjectProperty<ProjectStatus> status = new SimpleObjectProperty<>();
    private final ObjectProperty<ProjectCause> cause = new SimpleObjectProperty<>();
    private final ObjectProperty<Set<Tag>> tags = new SimpleObjectProperty<>();

    public Project(ProjectDto dto) {
        uid.set(dto.getUid());
        name.set(dto.getName());
        body.set(dto.getBody());
        plannedStartDate.set(dto.getPlannedStartDate());
        plannedEndDate.set(dto.getPlannedEndDate());
        realStartDate.set(dto.getRealStartDate());
        realEndDate.set(dto.getRealEndDate());
        materialsReadyDate.set(dto.getMaterialsReadyDate());
        workingHoursCount.set(dto.getWorkingHoursCount());
        workingHoursPlanned.set(dto.getWorkingHoursPlanned());
        projectLocation.set(dto.getProjectLocation());
        locationType.set(dto.getLocationType());
        status.set(dto.getStatus());
        cause.set(dto.getCause());
        if (dto.getTags() != null) {
            tags.set(dto.getTags().stream().map(Tag::new).collect(Collectors.toSet()));
        }
    }
}
