package org.epm.delivery.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.epm.common.model.IDTO;
import org.epm.delivery.enums.DeliveryStatus;
import org.epm.invoice.model.InvoiceEntity;
import org.epm.material.model.MaterialEntity;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@JsonTypeName("DeliveryDTO")
public class DeliveryDTO
        extends DeliveryData<DeliveryDTO> implements IDTO {

    private MaterialEntity material;
    private DeliveryStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "0.00")
    private Float unitPrice;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "0.00")
    private Float totalPrice;
    private Integer qty;
    private InvoiceEntity invoice;
    private String action;

    @JsonIgnore
    public boolean isValidDTO() {
        return getMaterial() != null || getStatus() != null || getUnitPrice() != null
                || getTotalPrice() != null || getQty() != null || getAction() != null
                || getInvoice() != null;
    }

}
