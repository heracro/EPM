package org.epm;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.epm.bom.repository.BomRepository;
import org.epm.delivery.repository.DeliveryRepository;
import org.epm.material.MaterialTestParameterProvider;
import org.epm.material.model.MaterialEntity;
import org.epm.material.repository.MaterialRepository;
import org.epm.project.repository.ProjectRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DatabaseSeeder {

    private final ProjectRepository projectRepository;
    private final MaterialRepository materialRepository;
    private final MaterialTestParameterProvider materialProvider;
    private final BomRepository bomRepository;
    private final DeliveryRepository deliveryRepository;

    private static final int MATERIALS_COUNT = 250;
    private final List<MaterialEntity> materials = new ArrayList<>();

    public void seed() {
        seedMaterials();
    }

    public void clear() {
        materialRepository.deleteAll();
    }

    private void seedMaterials() {
        materials.addAll(materialRepository.findAll());
        for (int i = materials.size(); i < MATERIALS_COUNT; ++i) {
            materials.add(materialRepository.save(materialProvider.provideValidEntity()));
        }
    }
}
