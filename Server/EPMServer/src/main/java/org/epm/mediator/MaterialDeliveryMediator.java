package org.epm.mediator;

import lombok.RequiredArgsConstructor;
import org.epm.delivery.DeliveryRepository;
import org.epm.delivery.model.DeliveryEntity;
import org.epm.material.MaterialRepository;
import org.epm.material.model.MaterialEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MaterialDeliveryMediator implements IMaterialDeliveryMediator {
    private final MaterialRepository materialRepository;
    private final DeliveryRepository deliveryRepository;

    @Override
    public Page<DeliveryEntity> findDeliveriesForMaterial(MaterialEntity materialEntity, Pageable pageable) {
        return deliveryRepository.findByMaterial(materialEntity, pageable);
    }
}
