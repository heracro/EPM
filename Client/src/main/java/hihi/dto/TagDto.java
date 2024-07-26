package hihi.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import hihi.dto.enums.TagType;
import hihi.gui.layout.content.tag.Tag;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonTypeName("Tag")
public class TagDto extends AbstractDto {

    private TagType tagType;
    private String name;

    public TagDto(Tag tag) {
        tagType = tag.getTagType().getValue();
        name = tag.getName().getValue();
    }

}
