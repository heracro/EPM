package hihi.content.project;

import hihi.content.common.dataModel.AbstractDto;
import hihi.content.enums.LocationType;
import hihi.content.enums.ProjectStatus;
import hihi.content.enums.ProjectCause;
import hihi.content.common.dataModel.AbstractContent;
import hihi.content.tag.Tag;
import javafx.beans.property.*;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@ToString(callSuper = true)
@NoArgsConstructor
public class Project extends AbstractContent {

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

    public StringProperty nameProperty() { return name; }
    public StringProperty bodyProperty() { return body; }
    public ObjectProperty<LocalDate> plannedStartDateProperty() { return plannedStartDate; }
    public ObjectProperty<LocalDate> plannedEndDateProperty() { return plannedEndDate; }
    public ObjectProperty<LocalDate> realStartDateProperty() { return realStartDate; }
    public ObjectProperty<LocalDate> realEndDateProperty() { return realEndDate; }
    public ObjectProperty<LocalDate> materialsReadyDateProperty() { return materialsReadyDate; }
    public IntegerProperty workingHoursCountProperty() { return workingHoursCount; }
    public IntegerProperty workingHoursPlannedProperty() { return workingHoursPlanned; }
    public StringProperty projectLocationProperty() { return projectLocation; }
    public ObjectProperty<LocationType> locationTypeProperty() { return locationType; }
    public ObjectProperty<ProjectStatus> statusProperty() { return status; }
    public ObjectProperty<ProjectCause> causeProperty() { return cause; }
    public ObjectProperty<Set<Tag>> tagsProperty() { return tags; }

}
