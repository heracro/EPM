package org.epm.bom;

import org.epm.ITestParameterProvider;
import org.epm.bom.model.BomDTO;
import org.epm.bom.model.BomEntity;
import org.epm.bom.model.BomStatus;
import org.epm.material.MaterialRepository;
import org.epm.material.model.MaterialMapper;
import org.epm.project.ProjectRepository;
import org.epm.project.model.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.stream.Stream;

@Component
public class BomTestParameterProvider implements ITestParameterProvider<BomEntity, BomDTO> {
    @Autowired
    private MaterialRepository materialRepository;
    @Autowired
    private MaterialMapper materialMapper;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ProjectMapper projectMapper;
    Random random = new Random();

    @Override
    public BomDTO createRandomValidDTO() throws Exception {
        BomDTO bom = new BomDTO();
        bom.setMaterial(materialMapper.toDto(materialRepository.findFirstByOrderByIdDesc()));
        bom.setProject(projectMapper.toDto(projectRepository.findFirstByOrderByIdDesc()));
        bom.setQty(10);
        bom.setReservedQty(0);
        bom.setStatus(BomStatus.MISSING);
        return bom;
    }

    @Override
    public Stream<BomDTO> createDTOWhichIsValidEntity() {

        return Stream.empty();
    }

    @Override
    public Stream<BomDTO> createDTOWhichIsInvalidEntity() {
        return Stream.empty();
    }
}
