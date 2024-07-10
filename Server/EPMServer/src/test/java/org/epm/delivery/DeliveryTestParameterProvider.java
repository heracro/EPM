package org.epm.delivery;

import org.epm.AbstractTestParameterProvider;
import org.epm.delivery.model.DeliveryDTO;
import org.epm.delivery.model.DeliveryEntity;
import org.epm.delivery.model.DeliveryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeliveryTestParameterProvider
        extends AbstractTestParameterProvider<DeliveryEntity, DeliveryDTO> {

    @Autowired
    private DeliveryMapper mapper;

    @Override
    protected int getDTOAttrCount() {
        return 8;
    }

    @Override
    protected DeliveryMapper getMapper() {
        return mapper;
    }

    @Override
    protected DeliveryEntity randomInstance() {
        return DeliveryEntity.randomInstance();
    }

    @Override
    protected DeliveryDTO emptyInstance() {
        return DeliveryDTO.emptyInstance();
    }

    @Override
    protected DeliveryDTO provideSingleAttribute(DeliveryDTO deliveryDTO, int caseNumber) {
        return deliveryDTO;
    }

    @Override
    protected DeliveryDTO breakSingleAttribute(DeliveryDTO deliveryDTO, int caseNumber) {
        return deliveryDTO;
    }
}
