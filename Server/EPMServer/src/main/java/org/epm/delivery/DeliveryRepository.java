package org.epm.delivery;

import org.epm.common.repository.IRepository;
import org.epm.delivery.model.DeliveryEntity;
import org.epm.material.model.MaterialEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends IRepository<DeliveryEntity> {
    Page<DeliveryEntity> findByMaterial(MaterialEntity materialEntity, Pageable pageable);
}
