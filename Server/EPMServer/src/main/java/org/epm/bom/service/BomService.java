package org.epm.bom.service;

import lombok.RequiredArgsConstructor;
import org.epm.bom.model.BomDTO;
import org.epm.bom.model.BomEntity;
import org.epm.bom.model.BomMapper;
import org.epm.bom.repository.BomRepository;
import org.epm.common.model.IMapper;
import org.epm.common.repository.IDependantRepository;
import org.epm.common.repository.IRepository;
import org.epm.common.service.AbstractDependantResourceService;
import org.epm.project.model.ProjectEntity;
import org.epm.project.repository.ProjectRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BomService extends
        AbstractDependantResourceService<BomEntity, ProjectEntity, BomDTO> {

    private final ProjectRepository projectRepository; //parent repo
    private final BomRepository bomRepository; //dependant repo
    private final BomMapper bomMapper;


    @Override
    public IMapper<BomEntity, BomDTO> getMapper() {
        return bomMapper;
    }

    @Override
    protected IRepository<ProjectEntity> getParentRepository() {
        return projectRepository;
    }

    @Override
    protected IDependantRepository<BomEntity> getRepository() {
        return bomRepository;
    }

    @Override
    public String getEntityName() {
        return "Bom";
    }

    @Override
    public String getParentEntityName() {
        return "Project";
    }
}
