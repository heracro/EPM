package hihi.content.delivery;

import com.fasterxml.jackson.annotation.JsonTypeName;
import hihi.content.common.dataModel.AbstractDto;
import hihi.content.enums.DeliveryStatus;
import hihi.content.enums.Unit;
import hihi.content.invoice.InvoiceDto;
import hihi.content.material.MaterialDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

}
