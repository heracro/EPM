package org.epm.changeLog.repository;

import org.epm.changeLog.model.ChangeLogEntity;
import org.epm.common.repository.IDependantRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChangeLogRepository extends IDependantRepository<ChangeLogEntity> {

    Page<ChangeLogEntity> findByProject_Uid(Integer projectUid, Pageable pageable);

    @Override
    default Page<ChangeLogEntity> findAllByParentUid(Integer projectUid, Pageable pageable) {
        return findByProject_Uid(projectUid, pageable);
    }

    Optional<ChangeLogEntity> findByProject_UidAndUid(Integer projectUid, Integer uid);

    @Override
    default Optional<ChangeLogEntity> findByParentUidAndUid(Integer projectUid, Integer uid) {
        return findByProject_UidAndUid(projectUid, uid);
    }
}
