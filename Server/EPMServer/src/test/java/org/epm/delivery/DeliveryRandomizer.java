package org.epm.delivery;


import org.epm.common.utils.RandomUtils;
import org.epm.delivery.enums.DeliveryStatus;
import org.epm.delivery.model.DeliveryDTO;
import org.epm.delivery.model.DeliveryData;
import org.epm.delivery.model.DeliveryEntity;
import org.epm.material.model.MaterialEntity;
import org.epm.material.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeliveryRandomizer {

    @Autowired
    private MaterialRepository materialRepository;

    public DeliveryDTO provideEmptyDTO() {
        return new DeliveryDTO();
    }

    public DeliveryEntity provideValidEntity() {
        return (DeliveryEntity) randomizeData(new DeliveryEntity());
    }

    public DeliveryData randomizeData(DeliveryData data) {
        return null;
    }

    public void setRandomMaterial(DeliveryData data) {
        MaterialEntity material = (MaterialEntity) data.getMaterial();
        do {
            data.setMaterial(materialRepository
                    .findTop10ByOrderByFreeQty()
                    .get(RandomUtils.randomInt(10)));
        } while (material != null && material.equals(data.getMaterial()));
    }

    public void setRandomStatus(DeliveryData data) {
        do {
            data.setStatus(
                    DeliveryStatus.values()[RandomUtils.randomInt(DeliveryStatus.values().length)]
            );
        } while (!data.isStatusOk());
    }

    public void setRandomUnitPrice(DeliveryData data) {
        data.setUnitPrice((RandomUtils.randomInt(10000) + 1) / 100f);
    }

    public void setRandomTotalPrice(DeliveryData data) {
        data.setTotalPrice((RandomUtils.randomInt(10000) + 1) / 100f);
    }
}
