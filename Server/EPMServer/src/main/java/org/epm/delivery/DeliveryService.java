package org.epm.delivery;

import org.epm.common.repository.IRepository;
import org.epm.common.service.AbstractEntityService;
import org.epm.delivery.model.DeliveryDTO;
import org.epm.delivery.model.DeliveryEntity;
import org.epm.delivery.model.DeliveryMapper;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService extends AbstractEntityService<DeliveryEntity, DeliveryDTO> {
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
