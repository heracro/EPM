package org.epm.delivery.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.epm.common.Config;
import org.epm.common.model.IDTO;
import org.epm.common.utils.RandomUtils;
import org.epm.material.model.MaterialEntity;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class DeliveryDTO implements IDTO {
    private MaterialEntity material;
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

    public boolean isValidDTO() {
        return material != null || status != null || unitPrice != null
                || totalPrice != null || qty != null || orderDate != null
                || deliveryDate != null || store != null || action != null;
    }

    public static DeliveryDTO randomInstance() {
        DeliveryDTO d = new DeliveryDTO();
        d.setStatus(DeliveryStatus.randomDeliveryStatus());
        if (d.getStatus() != DeliveryStatus.SHOPPING_LIST) {
            d.setUnitPrice(RandomUtils.randomInt(10000) / 100f);
            d.setQty(RandomUtils.randomInt(20));
            d.setTotalPrice(d.getUnitPrice() * d.getQty());
        }
        switch (d.getStatus()) {
            case DELIVERED:
                d.setDeliveryDate(RandomUtils.randomDate(Config.BIG_BANG_DATE, LocalDate.now().minusDays(2)));
            case ORDERED:
                d.setOrderDate(RandomUtils.randomDate(Config.BIG_BANG_DATE, d.getDeliveryDate().minusDays(4)));
        }
        d.setStore("Store " + RandomUtils.randomInt(120));
        return d;
    }
}
