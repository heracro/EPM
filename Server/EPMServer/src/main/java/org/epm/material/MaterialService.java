package org.epm.material;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.epm.delivery.model.Delivery;
import org.epm.material.model.Material;
import org.epm.mediator.IMaterialDeliveryMediator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MaterialService {
    private final MaterialRepository  materialRepository;
    private final IMaterialDeliveryMediator materialDeliveryMediator;
    private final MaterialMapper materialMapper;

    public Material createMaterial(Material material) {
        return materialRepository.save(material);
    }

    public Material replaceMaterial(Long mid, Material material) {
        if (!materialRepository.existsById(mid)) {
            return null;
        }
        material.setId(mid);
        return materialRepository.save(material);
    }

    public Page<Material> findAll(Pageable pageable) {
        return materialRepository.findAll(pageable);
    }

    public Optional<Material> findById(Long id) {
        return materialRepository.findById(id);
    }

    public List<Material> createMaterials(List<Material> materials) {
        return materialRepository.saveAll(materials);
    }

    public Material updateMaterial(Long id, Material material)
            throws IllegalArgumentException, EntityNotFoundException {
        Material existingMaterial = materialRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Material not found"));
        materialMapper.updateMaterialFromDto(existingMaterial, material);
        if (!existingMaterial.hasValidValues()) {
            throw new IllegalArgumentException("Material has invalid values");
        }
        return materialRepository.save(existingMaterial);
    }

    public void deleteMaterial(Long id) throws EntityNotFoundException {
        if (!materialRepository.existsById(id)) {
            throw new EntityNotFoundException("Material not found");
        }
        materialRepository.deleteById(id);
    }

    public Page<Delivery> findDeliveriesForMaterial(Long id, PageRequest pageRequest) {
        Material material = materialRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Material not found"));
        return materialDeliveryMediator.findDeliveriesForMaterial(material, pageRequest);
    }
}
