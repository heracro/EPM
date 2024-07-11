package org.epm.delivery.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.epm.common.configuration.Config;
import org.epm.common.model.IDTO;
import org.epm.common.utils.RandomUtils;
import org.epm.delivery.enums.DeliveryStatus;
import org.epm.material.model.MaterialEntity;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@JsonTypeName("DeliveryDTO")
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

    @JsonIgnore
    public boolean isValidDTO() {
        return getMaterial() != null || getStatus() != null || getUnitPrice() != null
                || getTotalPrice() != null || getQty() != null || getOrderDate() != null
                || getDeliveryDate() != null || getStore() != null || getAction() != null;
    }

    public static DeliveryDTO emptyInstance() {
        return new DeliveryDTO();
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
