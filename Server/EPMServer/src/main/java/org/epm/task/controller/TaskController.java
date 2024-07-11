package org.epm.task.controller;

import org.epm.common.controller.AbstractEntityController;
import org.epm.task.model.TaskDTO;
import org.epm.task.service.TaskService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/projects/{projectId}/tasks")
public class TaskController extends AbstractEntityController<TaskDTO> {

    public TaskController(TaskService taskService) {
        super(taskService);
    }

    @Override
    public String getMapping() {
        return "/api/projects/{projectId}/tasks";
    }
}
