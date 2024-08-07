package hihi.content.tag;

import com.fasterxml.jackson.annotation.JsonTypeName;
import hihi.content.common.AbstractDto;
import hihi.content.enums.TagType;
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
