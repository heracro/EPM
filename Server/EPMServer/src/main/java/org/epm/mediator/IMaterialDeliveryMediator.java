package org.epm.mediator;

import org.epm.delivery.model.Delivery;
import org.epm.material.model.Material;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IMaterialDeliveryMediator {
    Page<Delivery> findDeliveriesForMaterial(Material material, Pageable pageable);
}
