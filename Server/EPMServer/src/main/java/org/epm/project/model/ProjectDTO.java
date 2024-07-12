package org.epm.project.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;
import org.epm.common.model.IDTO;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@JsonTypeName("Project")
public class ProjectDTO extends ProjectData implements IDTO {

    private String action;

    public List<String> getAllowedActions() {
        return List.of("begin", "end");
    }

    @Override
    public String toString() {
        return super.toString().substring(0, super.toString().length()-2)
                + ", action: " + getAction() + "}";
    }

    @Override
    public boolean isValidDTO() {
        return super.isValidDTO() || getAction() != null;
    }
}
