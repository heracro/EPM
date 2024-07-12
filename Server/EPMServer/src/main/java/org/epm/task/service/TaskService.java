package org.epm.task.service;

import org.epm.common.service.AbstractService;
import org.epm.task.model.TaskDTO;
import org.epm.task.model.TaskEntity;
import org.epm.task.model.TaskMapper;
import org.epm.task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService extends AbstractService<TaskEntity, TaskDTO> {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        super(taskMapper);
        this.taskRepository = taskRepository;
    }

    @Override
    protected TaskRepository getRepository() {
        return taskRepository;
    }

    @Override
    public String getEntityName() {
        return "Task";
    }
}
