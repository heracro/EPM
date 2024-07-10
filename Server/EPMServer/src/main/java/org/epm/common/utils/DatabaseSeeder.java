package org.epm.common.utils;

import lombok.RequiredArgsConstructor;
import org.epm.bom.BomRepository;
import org.epm.bom.model.BomEntity;
import org.epm.bom.model.BomMapper;
import org.epm.delivery.DeliveryRepository;
import org.epm.delivery.model.DeliveryEntity;
import org.epm.delivery.model.DeliveryMapper;
import org.epm.material.MaterialRepository;
import org.epm.material.model.MaterialEntity;
import org.epm.material.model.MaterialMapper;
import org.epm.project.ProjectRepository;
import org.epm.project.model.ProjectEntity;
import org.epm.project.model.ProjectMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class DatabaseSeeder {

    private ProjectRepository projectRepository;
    private ProjectMapper projectMapper;
    private MaterialRepository materialRepository;
    private MaterialMapper materialMapper;
    private BomRepository bomRepository;
    private BomMapper bomMapper;
    private DeliveryRepository deliveryRepository;
    private DeliveryMapper deliveryMapper;

    private final Random random = new Random();

    private final int scale = 10;

    public void seed() {
        List<MaterialEntity> materials = new ArrayList<>();
        for (long i = materialRepository.count(); i < 10 * scale; ++i) {
            materials.add(materialRepository.save(MaterialEntity.randomInstance()));
        }
        List<ProjectEntity> projects = new ArrayList<>();
        for (long i = projectRepository.count(); i < 2 * scale; ++i) {
            projects.add(projectRepository.save(ProjectEntity.randomInstance()));
        }
        for (long i = bomRepository.count(); i < 5 * scale; ++i) {
            BomEntity bom = BomEntity.randomInstance();
            bom.setMaterial(materials.get(RandomUtils.randomInt(materials.size())));
            bom.propagateRandomProject(projects.get(RandomUtils.randomInt(projects.size())));
            bomRepository.save(bom);
        }
        for (long i = deliveryRepository.count(); i < 3 * scale; ++i) {
            DeliveryEntity d = DeliveryEntity.randomInstance();
            d.setMaterial(materials.get(RandomUtils.randomInt(materials.size())));
        }
    }

    public void clear() {
        deliveryRepository.deleteAll();
        bomRepository.deleteAll();
        projectRepository.deleteAll();
        materialRepository.deleteAll();
    }

}
