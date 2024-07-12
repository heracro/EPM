package org.epm.delivery.service;

import org.epm.common.repository.IRepository;
import org.epm.common.service.AbstractService;
import org.epm.delivery.model.DeliveryDTO;
import org.epm.delivery.model.DeliveryEntity;
import org.epm.delivery.model.DeliveryMapper;
import org.epm.delivery.repository.DeliveryRepository;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService extends AbstractService<DeliveryEntity, DeliveryDTO> {
    private final DeliveryRepository deliveryRepository;

    public DeliveryService(DeliveryRepository deliveryRepository,
                           DeliveryMapper deliveryMapper) {
        super(deliveryMapper);
        this.deliveryRepository = deliveryRepository;
    }

    @Override
    public IRepository<DeliveryEntity> getRepository() {
        return deliveryRepository;
    }

    @Override
    public String getEntityName() {
        return "Delivery";
    }
}
