package org.epm.invoice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.epm.common.model.IDTO;
import org.epm.invoice.enums.InvoiceStatus;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@JsonTypeName("Invoice")
public class InvoiceDTO
        extends InvoiceData implements IDTO {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate orderDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate deliveryDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate invoiceDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate paymentDueDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate paymentDate;
    private String invoiceNumber;
    private String parcelTrackingNumber;
    private String store;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "0.00")
    private Float totalAmount;
    private InvoiceStatus status;
    private String action;

    @JsonIgnore
    public boolean isValidDTO() {
        return orderDate != null || deliveryDate != null || invoiceDate != null
                || paymentDueDate != null || paymentDate != null || invoiceNumber != null
                || parcelTrackingNumber != null || store != null || totalAmount != null
                || status != null || action != null;
    }

}
