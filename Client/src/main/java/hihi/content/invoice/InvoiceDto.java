package hihi.content.invoice;

import hihi.content.common.dataModel.AbstractDto;
import hihi.content.shop.ShopDto;
import hihi.content.enums.InvoiceStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class InvoiceDto extends AbstractDto {

    private LocalDate orderDate;
    private LocalDate plannedDate;
    private LocalDate deliveryDate;
    private LocalDate invoiceDate;
    private LocalDate paymentDueDate;
    private LocalDate paymentDate;
    private String invoiceNumber;
    private String parcelTrackingNumber;
    private Float totalAmount;
    private InvoiceStatus status;
    private ShopDto shop;

    public InvoiceDto(Invoice invoice) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
