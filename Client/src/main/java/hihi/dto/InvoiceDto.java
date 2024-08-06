package hihi.dto;

import hihi.dto.enums.InvoiceStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class InvoiceDto {

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

}
