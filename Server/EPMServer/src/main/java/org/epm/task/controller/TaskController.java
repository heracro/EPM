package org.epm.task.controller;

import org.epm.common.controller.AbstractRestController;
import org.epm.task.model.TaskDTO;
import org.epm.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/projects/{projectId}/tasks")
public class TaskController extends AbstractRestController<TaskDTO> {

    @Autowired
    public TaskController(TaskService taskService) {
        super(taskService);
    }

    @Override
    public String getMapping() {
        return "/projects/{projectId}/tasks";
    }
}
