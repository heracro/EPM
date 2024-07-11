package org.epm.material.service;

import org.epm.common.service.AbstractEntityService;
import org.epm.material.model.MaterialDTO;
import org.epm.material.model.MaterialEntity;
import org.epm.material.model.MaterialMapper;
import org.epm.material.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaterialService extends AbstractEntityService<MaterialEntity, MaterialDTO> {

    private final MaterialRepository materialRepository;

    @Autowired
    public MaterialService(MaterialRepository materialRepository,
                           MaterialMapper materialMapper) {
        super(materialMapper);
        this.materialRepository = materialRepository;
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
















