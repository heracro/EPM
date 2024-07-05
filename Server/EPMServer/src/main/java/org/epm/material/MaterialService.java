package org.epm.material;

import jakarta.persistence.EntityNotFoundException;
import org.epm.common.repository.IRepository;
import org.epm.common.service.AbstractEntityService;
import org.epm.delivery.model.DeliveryDTO;
import org.epm.delivery.model.DeliveryEntity;
import org.epm.delivery.model.DeliveryMapper;
import org.epm.material.model.MaterialDTO;
import org.epm.material.model.MaterialEntity;
import org.epm.material.model.MaterialMapper;
import org.epm.mediator.IMaterialDeliveryMediator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MaterialService
        extends AbstractEntityService<MaterialEntity, MaterialDTO> {
    private final MaterialRepository  materialRepository;
    private final IMaterialDeliveryMediator materialDeliveryMediator;
    private final DeliveryMapper deliveryMapper;

    public MaterialService(MaterialRepository materialRepository,
                           IMaterialDeliveryMediator materialDeliveryMediator,
                           MaterialMapper materialMapper, DeliveryMapper deliveryMapper) {
        super(materialMapper);
        this.materialRepository = materialRepository;
        this.materialDeliveryMediator = materialDeliveryMediator;
        this.deliveryMapper = deliveryMapper;
    }

    public Page<DeliveryDTO> findDeliveriesForMaterial(final Long id, final Pageable pageable)
            throws EntityNotFoundException {
        MaterialEntity material = materialRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Material not found"));
        Page<DeliveryEntity> deliveries = materialDeliveryMediator
                .findDeliveriesForMaterial(material, pageable);
        return deliveries.map(deliveryMapper::toDto);
    }

    @Override
    public IRepository<MaterialEntity> getRepository() {
        return materialRepository;
    }

    @Override
    public String getEntityName() {
        return "Material";
    }
}
















