package org.epm.delivery;

import org.epm.delivery.model.Delivery;
import org.epm.material.model.Material;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    Page<Delivery> findByMaterial(Material material, Pageable pageable);
}
