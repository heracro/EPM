package org.epm.tag.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.epm.common.model.IDTO;

@Getter
@Setter
@NoArgsConstructor
@JsonTypeName("Tag")
public class TagDTO extends TagData implements IDTO {

    private String action;

    @Override
    @JsonIgnore
    public boolean isValidDTO() {
        return super.isValidDTO() || getAction() != null;
    }

    @Override
    public String toString() {
        return super.toString().substring(0, super.toString().length()-2)
                + ", action: " + getAction() + "}";
    }
}
