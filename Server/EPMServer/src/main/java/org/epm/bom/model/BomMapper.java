package org.epm.bom.model;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.epm.common.model.IMapper;
import org.epm.material.model.MaterialMapper;
import org.epm.material.repository.MaterialRepository;
import org.epm.project.model.ProjectMapper;
import org.epm.project.repository.ProjectRepository;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class BomMapper implements IMapper<BomEntity, BomDTO> {

    private final ProjectMapper projectMapper;
    private final MaterialMapper materialMapper;
    private final ProjectRepository projectRepository;
    private final MaterialRepository materialRepository;



    public BomDTO toDto(@NonNull BomEntity bom) {
        BomDTO result = new BomDTO();
        result.setUid(bom.getUid());
        result.setProjectUid(bom.getProject().getUid());
        result.setMaterialUid(bom.getMaterial().getUid());
        result.setStatus(bom.getStatus());
        result.setQty(bom.getQty());
        result.setReservedQty(bom.getReservedQty());
        return result;
    }

    /**
     * TODO: Optimize!
     * @param bom
     * @return
     */
    public BomEntity toEntity(@NonNull BomDTO bom) {
        BomEntity result = new BomEntity();
        result.setUid(bom.getUid());
        if (bom.getProjectUid() != null) {
            result.setProject(projectRepository.findByUid(bom.getProjectUid()).orElseThrow(
                    () -> new RuntimeException("Database inconsistent: non-existing project is somehow assigned to Bom")
            ));
        }
        if (bom.getMaterialUid() != null) {
            result.setMaterial(materialRepository.findByUid(bom.getMaterialUid()).orElseThrow(
                    () -> new RuntimeException("Database inconsistent: non-existing project is somehow assigned to Bom")
            ));
        }
        result.setStatus(bom.getStatus());
        result.setQty(bom.getQty());
        result.setReservedQty(bom.getReservedQty());
        return result;
    }

    @Override
    public void updateEntityFromDto(@NonNull BomDTO bom, @NonNull BomEntity entity) {
        BomEntity updater = toEntity(bom);
        if (updater.getProject() != null) entity.setProject(updater.getProject()); //should it be allowed??
        if (updater.getMaterial() != null) entity.setMaterial(updater.getMaterial()); //should it be allowed??
        if (updater.getStatus() != null) entity.setStatus(updater.getStatus());
        if (updater.getQty() != null) entity.setQty(updater.getQty());
        if (updater.getReservedQty() != null) entity.setReservedQty(updater.getReservedQty());
    }

}
