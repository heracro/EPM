package hihi.content.changelog;

import hihi.content.common.dataModel.AbstractContent;
import hihi.content.enums.ChangeLogSource;
import hihi.content.enums.ChangeType;
import javafx.beans.property.*;

import java.time.LocalDateTime;

public class ChangeLog extends AbstractContent {

    private IntegerProperty projectUid = new SimpleIntegerProperty();
    private StringProperty projectName = new SimpleStringProperty();
    private ObjectProperty<LocalDateTime> timestamp = new SimpleObjectProperty<>();
    private ObjectProperty<ChangeLogSource> source = new SimpleObjectProperty<>();
    private ObjectProperty<ChangeType> changeType = new SimpleObjectProperty<>();
    private StringProperty description = new SimpleStringProperty();
    private StringProperty author = new SimpleStringProperty();

    public ChangeLog(ChangeLogDto dto) {
        projectUid.set(dto.getProject().getUid());
        projectName.set(dto.getProject().getName());
        timestamp.set(dto.getTimestamp());
        source.set(dto.getSource());
        changeType.set(dto.getChangeType());
        description.set(dto.getDescription());
        author.set(dto.getAuthor());
    }

    public IntegerProperty projectUidProperty() { return projectUid; }
    public StringProperty projectNameProperty() { return projectName; }
    public ObjectProperty<LocalDateTime> timestampProperty() { return timestamp; }
    public ObjectProperty<ChangeLogSource> sourceProperty() { return source; }
    public ObjectProperty<ChangeType> changeTypeProperty() { return changeType; }
    public StringProperty descriptionProperty() { return description; }
    public StringProperty authorProperty() { return author; }
}
