package org.epm.project.service;

import lombok.RequiredArgsConstructor;
import org.epm.common.service.AbstractService;
import org.epm.project.model.ProjectDTO;
import org.epm.project.model.ProjectEntity;
import org.epm.project.model.ProjectMapper;
import org.epm.project.repository.ProjectRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectService extends AbstractService<ProjectEntity, ProjectDTO> {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    @Override
    public ProjectMapper getMapper() {
        return projectMapper;
    }

    @Override
    protected ProjectRepository getRepository() {
        return projectRepository;
    }

    @Override
    public String getEntityName() {
        return "Project";
    }

}
