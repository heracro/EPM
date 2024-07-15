package org.epm.task.controller;

import lombok.RequiredArgsConstructor;
import org.epm.common.controller.AbstractRestController;
import org.epm.task.model.TaskDTO;
import org.epm.task.service.TaskService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/projects/{parentUid}/tasks")
@RequiredArgsConstructor
public class TaskController extends AbstractRestController<TaskDTO> {

    private final TaskService taskService;

    @Override
    public TaskService getEntityService() {
        return taskService;
    }

}
