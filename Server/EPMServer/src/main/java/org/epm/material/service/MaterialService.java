package org.epm.material.service;

import lombok.RequiredArgsConstructor;
import org.epm.common.service.AbstractService;
import org.epm.material.model.MaterialDTO;
import org.epm.material.model.MaterialEntity;
import org.epm.material.model.MaterialMapper;
import org.epm.material.repository.MaterialRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MaterialService extends AbstractService<MaterialEntity, MaterialDTO> {

    private final MaterialRepository materialRepository;
    private final MaterialMapper materialMapper;

    @Override
    public MaterialMapper getMapper() {
        return materialMapper;
    }

    @Override
    public MaterialRepository getRepository() {
        return materialRepository;
    }

    @Override
    public String getEntityName() {
        return "Material";
    }
}
















