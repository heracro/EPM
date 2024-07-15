package org.epm.task.service;

import lombok.RequiredArgsConstructor;
import org.epm.common.service.AbstractDependantResourceService;
import org.epm.project.model.ProjectEntity;
import org.epm.project.repository.ProjectRepository;
import org.epm.task.model.TaskDTO;
import org.epm.task.model.TaskEntity;
import org.epm.task.model.TaskMapper;
import org.epm.task.repository.TaskRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService extends AbstractDependantResourceService<TaskEntity, ProjectEntity, TaskDTO> {

    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    public TaskMapper getMapper() {
        return taskMapper;
    }

    @Override
    protected ProjectRepository getParentRepository() {
        return projectRepository;
    }

    @Override
    protected TaskRepository getRepository() {
        return taskRepository;
    }

    @Override
    public String getEntityName() {
        return "Task";
    }

    @Override
    public String getParentEntityName() {
        return "Project";
    }
}
