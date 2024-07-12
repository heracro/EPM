package org.epm.tag.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.epm.common.model.IDTO;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@JsonTypeName("Tag")
public class TagDTO extends TagData implements IDTO {

    private String action;
}
