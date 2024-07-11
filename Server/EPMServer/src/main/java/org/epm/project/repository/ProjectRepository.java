package org.epm.project.repository;

import org.epm.common.repository.IRepository;
import org.epm.project.model.ProjectEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends IRepository<ProjectEntity> {
}
