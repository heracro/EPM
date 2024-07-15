package org.epm.mediator;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.epm.delivery.repository.DeliveryRepository;
import org.epm.delivery.model.DeliveryDTO;
import org.epm.delivery.model.DeliveryMapper;
import org.epm.material.repository.MaterialRepository;
import org.epm.material.model.MaterialEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MaterialDeliveryMediator implements IMaterialDeliveryMediator {
    private final MaterialRepository materialRepository;
    private final DeliveryRepository deliveryRepository;
    private final DeliveryMapper deliveryMapper;

    @Override
    public Page<DeliveryDTO> findDeliveriesForMaterialId(Long materialId, Pageable pageable) {
        MaterialEntity materialEntity = materialRepository
                .findById(materialId)
                .orElseThrow(() -> new EntityNotFoundException("Material not found"));
        return deliveryRepository.findByMaterial(materialEntity, pageable).map(deliveryMapper::toDto);
    }
}
