package hihi.content.tag;

import hihi.content.enums.TagType;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Tag {

    private final ObjectProperty<TagType> tagType = new SimpleObjectProperty<>();
    private final StringProperty name = new SimpleStringProperty();

    public Tag(TagDto dto) {
        tagType.set(dto.getTagType());
        name.set(dto.getName());
    }
}
