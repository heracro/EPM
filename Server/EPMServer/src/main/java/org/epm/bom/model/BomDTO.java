package org.epm.bom.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.epm.common.model.IDTO;

@Getter
@Setter
@NoArgsConstructor
@JsonTypeName("Bom")
public class BomDTO extends BomData implements IDTO {

    private Integer projectUid;

    private Integer materialUid;

    private String action;

    @JsonIgnore
    @Override
    public boolean isValidDTO() {
        return getStatus() != null || getQty() != null || getReservedQty() != null
                || getProjectUid() != null || getMaterialUid() != null
                || getAction() != null;
    }

}
