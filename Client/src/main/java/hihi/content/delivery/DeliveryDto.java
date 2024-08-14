package hihi.content.delivery;

import com.fasterxml.jackson.annotation.JsonTypeName;
import hihi.content.common.dataModel.AbstractDto;
import hihi.content.enums.DeliveryStatus;
import hihi.content.enums.Unit;
import hihi.content.invoice.InvoiceDto;
import hihi.content.material.MaterialDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@JsonTypeName("Delivery")
public class DeliveryDto extends AbstractDto {

    private String type;
    private MaterialDto material;
    private InvoiceDto invoice;
    private DeliveryStatus status;
    private Float unitPrice;
    private Float totalPrice;
    private Float qty;
    private Unit unit;

    public DeliveryDto(Delivery content) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
