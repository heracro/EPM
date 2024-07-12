package org.epm.changeLog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.epm.common.model.IDTO;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@JsonTypeName("ChangeLog")
public class ChangeLogDTO extends ChangeLogData implements IDTO {

    private String action;

    @JsonIgnore
    @Override
    public boolean isValidDTO() {
        return super.isValidDTO() || getAction() != null;
    }

    @Override
    public String toString() {
        return super.toString().substring(0, super.toString().length()-2)
                + ", action: " + getAction() + "}";
    }
}
