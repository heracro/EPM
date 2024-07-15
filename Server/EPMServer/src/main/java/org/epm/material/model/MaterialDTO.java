package org.epm.material.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.epm.common.model.IDTO;

@Getter
@Setter
@NoArgsConstructor
@JsonTypeName("Material")
public class MaterialDTO extends MaterialData implements IDTO {

    private String action;

    @Override
    @JsonIgnore
    public boolean isValidDTO() {
        return getId() != null || getName() != null || getNorm() != null
                || getDatasheet() != null || getDimensions() != null || getDescription() != null
                || getWeight() != null || getTotalQty() != null || getFreeQty() != null
                || getUnit() != null || getAction() != null;
    }

}
