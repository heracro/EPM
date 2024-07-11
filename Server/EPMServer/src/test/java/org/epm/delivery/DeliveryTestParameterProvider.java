package org.epm.delivery;

import jakarta.validation.constraints.NotNull;
import org.epm.AbstractTestParameterProvider;
import org.epm.delivery.model.DeliveryDTO;
import org.epm.delivery.model.DeliveryEntity;
import org.epm.delivery.model.DeliveryMapper;
import org.epm.material.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeliveryTestParameterProvider
        extends AbstractTestParameterProvider<DeliveryEntity, DeliveryDTO> {

    @Autowired
    private DeliveryMapper mapper;
    @Autowired
    private MaterialRepository materialRepository;
    @Autowired
    private DeliveryRandomizer randomizer;

    @Override
    protected int getDTOAttrCount() {
        return 8;
    }

    @Override
    protected DeliveryMapper getMapper() {
        return mapper;
    }

    @Override
    protected DeliveryEntity randomValidEntity() {
        return randomizer.provideValidEntity();
    }

    @Override
    protected DeliveryDTO emptyDTO() {
        return randomizer.provideEmptyDTO();
    }

    @Override
    protected DeliveryDTO provideSingleAttribute(@NotNull DeliveryDTO dto, int caseNumber) {
        switch (caseNumber % getDTOAttrCount()) {
            case 0 -> dto.setMaterial(randomValidEntity().getMaterial());
            case 1 -> dto.setStatus(randomValidEntity().getStatus());
            case 2 -> dto.setUnitPrice(randomValidEntity().getUnitPrice());
            case 3 -> dto.setTotalPrice(randomValidEntity().getTotalPrice());
            case 4 -> dto.setQty(randomValidEntity().getQty());
            case 5 -> dto.setOrderDate(randomValidEntity().getOrderDate());
            case 6 -> dto.setDeliveryDate(randomValidEntity().getDeliveryDate());
            case 7 -> dto.setStore(randomValidEntity().getStore());
        }
        return dto;
    }

    @Override
    protected DeliveryDTO breakSingleAttribute(DeliveryDTO dto, int caseNumber) {
        return dto;
    }
}
