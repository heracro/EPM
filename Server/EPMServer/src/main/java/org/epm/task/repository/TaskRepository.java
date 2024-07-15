package org.epm.task.repository;

import org.epm.common.repository.IDependantRepository;
import org.epm.task.model.TaskEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends IDependantRepository<TaskEntity> {

    Page<TaskEntity> findByProject_Uid(Integer projectUid, Pageable pageable);

    @Override
    default Page<TaskEntity> findAllByParentUid(Integer projectUid, Pageable pageable) {
        return findByProject_Uid(projectUid, pageable);
    }

    Optional<TaskEntity> findByProject_UidAndUid(Integer projectUid, Integer uid);

    @Override
    default Optional<TaskEntity> findByParentUidAndUid(Integer projectUid, Integer uid) {
        return findByProject_UidAndUid(projectUid, uid);
    }

}
