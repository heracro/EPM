package org.epm.project;

import org.epm.GenericControllerTest;
import org.epm.common.model.IMapper;
import org.epm.project.controller.ProjectController;
import org.epm.project.model.ProjectDTO;
import org.epm.project.model.ProjectEntity;
import org.epm.project.model.ProjectMapper;
import org.epm.project.repository.ProjectRepository;
import org.epm.project.service.ProjectService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProjectControllerTest
        extends GenericControllerTest<ProjectEntity, ProjectDTO> {

    @Autowired
    private ProjectController projectController;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ProjectTestParameterProvider projectTestParameterProvider;
    @Autowired
    private ProjectMapper projectMapper;

    @Override
    protected IMapper<ProjectEntity, ProjectDTO> getMapper() {
        return projectMapper;
    }

    @Override
    protected ProjectTestParameterProvider getTestParameterProvider() {
        return projectTestParameterProvider;
    }

    @Override
    protected ProjectController getController() {
        return projectController;
    }

    @Override
    protected ProjectService getService() {
        return projectService;
    }

    @Override
    protected ProjectRepository getRepository() {
        return projectRepository;
    }
}
