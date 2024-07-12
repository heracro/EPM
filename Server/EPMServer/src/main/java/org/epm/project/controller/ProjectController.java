package org.epm.project.controller;

import lombok.extern.slf4j.Slf4j;
import org.epm.common.controller.AbstractRestController;
import org.epm.project.model.ProjectDTO;
import org.epm.project.service.ProjectService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/projects")
public class ProjectController extends AbstractRestController<ProjectDTO> {

    public ProjectController(ProjectService projectService) {
        super(projectService);
    }

    @Override
    public String getMapping() {
        return "/projects";
    }
}




