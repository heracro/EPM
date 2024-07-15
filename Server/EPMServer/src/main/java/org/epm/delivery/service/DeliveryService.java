package org.epm.delivery.service;

import lombok.RequiredArgsConstructor;
import org.epm.common.service.AbstractService;
import org.epm.delivery.model.DeliveryDTO;
import org.epm.delivery.model.DeliveryEntity;
import org.epm.delivery.model.DeliveryMapper;
import org.epm.delivery.repository.DeliveryRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryService extends AbstractService<DeliveryEntity, DeliveryDTO> {

    private final DeliveryRepository deliveryRepository;
    private final DeliveryMapper deliveryMapper;


    @Override
    public DeliveryMapper getMapper() {
        return deliveryMapper;
    }

    @Override
    public DeliveryRepository getRepository() {
        return deliveryRepository;
    }

    @Override
    public String getEntityName() {
        return "Delivery";
    }
}
