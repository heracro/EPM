package org.epm.delivery.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;
import org.epm.common.model.IDTO;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@JsonTypeName("Delivery")
public class DeliveryDTO extends DeliveryData implements IDTO {

    private String action;

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
