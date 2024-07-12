package org.epm.changeLog.repository;

import org.epm.changeLog.model.ChangeLogEntity;
import org.epm.common.repository.IRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChangeLogRepository extends IRepository<ChangeLogEntity> {
}
