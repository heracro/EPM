package org.epm.delivery.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.epm.common.model.IDTO;
import org.epm.invoice.model.InvoiceDTO;
import org.epm.material.model.MaterialDTO;
import org.epm.material.model.MaterialData;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
@JsonTypeName("Delivery")
public class DeliveryDTO extends DeliveryData implements IDTO {

    private MaterialDTO material;
    private InvoiceDTO invoice;
    private String action;

    @Override
    public void setMaterial(MaterialData material) {
        this.material = (MaterialDTO) material;
    }
}
