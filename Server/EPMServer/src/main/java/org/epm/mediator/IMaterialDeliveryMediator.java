package org.epm.mediator;

import org.epm.delivery.model.DeliveryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IMaterialDeliveryMediator {
    Page<DeliveryDTO> findDeliveriesForMaterialId(Long materialId, Pageable pageable);
}
