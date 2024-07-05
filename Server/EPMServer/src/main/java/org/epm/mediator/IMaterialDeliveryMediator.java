package org.epm.mediator;

import org.epm.delivery.model.DeliveryEntity;
import org.epm.material.model.MaterialEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IMaterialDeliveryMediator {
    Page<DeliveryEntity> findDeliveriesForMaterial(MaterialEntity materialEntity, Pageable pageable);
}
