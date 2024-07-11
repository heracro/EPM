package org.epm.common.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.epm.bom.repository.BomRepository;
import org.epm.bom.model.BomEntity;
import org.epm.delivery.repository.DeliveryRepository;
import org.epm.delivery.model.DeliveryEntity;
import org.epm.material.repository.MaterialRepository;
import org.epm.material.model.MaterialEntity;
import org.epm.project.ProjectRepository;
import org.epm.project.model.ProjectEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DatabaseSeeder {

    private final ProjectRepository projectRepository;
    private final MaterialRepository materialRepository;
    private final BomRepository bomRepository;
    private final DeliveryRepository deliveryRepository;

    private static final int scale = 10;

    public void seed() {
        log.info("\033[36mseed()\033[0m");
        int matCount = (int) materialRepository.count();
        int bomCount = (int) bomRepository.count();
        int deliveryCount = (int) deliveryRepository.count();
        int projectCount = (int) projectRepository.count();
        if (matCount >= 10 * scale && projectCount >= 2 * scale
                && bomCount >= 5 * scale && deliveryCount >= 3 * scale) return;
        log.info("\033[36mNow in db:\n\t\tProjects: {}\n\t\tMaterials: {}\n\t\tBoms: {}\n\t\tDeliveries: {}\033[0m",
                projectCount, matCount, bomCount, deliveryCount);
        List<MaterialEntity> materials = new ArrayList<>(materialRepository.findAll());
        for (int i = matCount; i < 10 * scale; ++i) {
            log.info("\033[36mAdding material index {}\033[0m", i);
            materials.add(materialRepository.save(MaterialEntity.randomInstance()));
            log.info("\033[36mAdded material {}\033[0m", materials.get(i));
        }
        List<ProjectEntity> projects = new ArrayList<>(projectRepository.findAll());
        for (int i = projectCount; i < 2 * scale; ++i) {
            log.info("\033[36mAdding project index {}\033[0m", i);
            projects.add(projectRepository.save(ProjectEntity.randomInstance()));
            log.info("\033[36mAdded project {}\033[0m", projects.get(i));
        }
        for (int i = bomCount; i < 5 * scale; ++i) {
            log.info("\033[36mAdding bom index {}\033[0m", i);
            BomEntity bom = BomEntity.randomInstance();
            bom.setMaterial(materials.get(RandomUtils.randomInt(materials.size())));
            bom.setRandomProject(projects.get(RandomUtils.randomInt(projects.size())));
            bomRepository.save(bom);
            log.info("\033[36mAdded bom {}\033[0m", bom);
        }
        for (int i = deliveryCount; i < 3 * scale; ++i) {
            log.info("\033[36mAdding delivery index {}\033[0m", i);
            DeliveryEntity d = DeliveryEntity.randomInstance();
            d.setMaterial(materials.get(RandomUtils.randomInt(materials.size())));
            deliveryRepository.save(d);
            log.info("\033[36mAdded delivery {}\033[0m", d);
        }
        log.info("\033[36mSeeding completed!\033[0m");
    }

    public void clear() {
        //deliveryRepository.deleteAll();
        //bomRepository.deleteAll();
        //projectRepository.deleteAll();
        //materialRepository.deleteAll();
    }

}
