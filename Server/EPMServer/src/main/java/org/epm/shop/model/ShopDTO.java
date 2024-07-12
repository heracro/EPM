package org.epm.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.epm.common.model.IDTO;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@JsonTypeName("Shop")
public class ShopDTO extends ShopData implements IDTO {

    private String action;

    @JsonIgnore
    @Override
    public boolean isValidDTO() {
        return false;
    }

}
