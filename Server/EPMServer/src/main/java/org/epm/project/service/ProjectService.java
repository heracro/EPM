package org.epm.project.service;

import org.epm.common.service.AbstractService;
import org.epm.project.model.ProjectDTO;
import org.epm.project.model.ProjectEntity;
import org.epm.project.model.ProjectMapper;
import org.epm.project.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService extends AbstractService<ProjectEntity, ProjectDTO> {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository,
                          ProjectMapper projectMapper) {
        super(projectMapper);
        this.projectRepository = projectRepository;
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
