package org.epm.project;

import org.epm.GenericMainResourcesControllerTest;
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
        extends GenericMainResourcesControllerTest<ProjectEntity, ProjectDTO> {

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
    protected ProjectTestParameterProvider getTestParameterProvider() {
        return projectTestParameterProvider;
    }

    @Override
    protected String getMapping() {
        return "/projects";
    }

}
