package org.epm.delivery.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.epm.common.model.IDTO;
import org.epm.material.model.MaterialEntity;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class DeliveryDTO implements IDTO {
    private MaterialEntity materialEntity;
    private DeliveryStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "0.00")
    private Float unitPrice;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "0.00")
    private Float totalPrice;
    private Integer qty;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate orderDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate deliveryDate;
    private String store;
    private String action;

    public boolean isValidEntity() {
        return materialEntity != null && status != null && unitPrice != null
                && unitPrice >= 0 && totalPrice != null && totalPrice >= 0
                && qty != null && qty > 0 && qty * unitPrice == totalPrice
                && (store == null || store.length() >= 4);
    }

    public boolean isValidDTO() {
        return materialEntity != null || status != null || unitPrice != null
                || totalPrice != null || qty != null || orderDate != null
                || deliveryDate != null || store != null || action != null;
    }
}
