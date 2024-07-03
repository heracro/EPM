package org.epm.material;

import lombok.RequiredArgsConstructor;
import org.epm.material.model.Material;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MaterialService {
    private final MaterialRepository  materialRepository;

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
}
