package org.epm.task.repository;

import org.epm.common.repository.IRepository;
import org.epm.task.model.TaskEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends IRepository<TaskEntity> {
}
