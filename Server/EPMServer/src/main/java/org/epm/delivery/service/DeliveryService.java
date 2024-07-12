package org.epm.delivery.service;

import org.epm.common.service.AbstractService;
import org.epm.delivery.model.DeliveryDTO;
import org.epm.delivery.model.DeliveryEntity;
import org.epm.delivery.model.DeliveryMapper;
import org.epm.delivery.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService extends AbstractService<DeliveryEntity, DeliveryDTO> {

    private final DeliveryRepository deliveryRepository;

    @Autowired
    public DeliveryService(DeliveryRepository deliveryRepository,
                           DeliveryMapper deliveryMapper) {
        super(deliveryMapper);
        this.deliveryRepository = deliveryRepository;
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
