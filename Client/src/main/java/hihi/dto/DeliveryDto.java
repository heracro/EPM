package hihi.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import hihi.dto.enums.DeliveryStatus;
import hihi.dto.enums.Unit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("Delivery")
public class DeliveryDto extends AbstractDto {

    private MaterialDto material;
    private InvoiceDto invoice;
    private DeliveryStatus status;
    private Float unitPrice;
    private Float totalPrice;
    private Integer qty;
    private Unit unit;

}
