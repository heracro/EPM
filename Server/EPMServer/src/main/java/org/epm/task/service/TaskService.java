package org.epm.task.service;

import lombok.RequiredArgsConstructor;
import org.epm.common.service.AbstractService;
import org.epm.task.model.TaskDTO;
import org.epm.task.model.TaskEntity;
import org.epm.task.model.TaskMapper;
import org.epm.task.repository.TaskRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService extends AbstractService<TaskEntity, TaskDTO> {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    public TaskMapper getMapper() {
        return taskMapper;
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
