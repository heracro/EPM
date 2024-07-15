package org.epm.bom.repository;

import org.epm.bom.model.BomEntity;
import org.epm.common.repository.IDependantRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BomRepository extends IDependantRepository<BomEntity> {

    Page<BomEntity> findByProject_Uid(Integer projectUid, Pageable pageable);

    @Override
    default Page<BomEntity> findAllByParentUid(Integer projectUid, Pageable pageable) {
        return findByProject_Uid(projectUid, pageable);
    }

    Optional<BomEntity> findByProject_UidAndUid(Integer projectUid, Integer uid);

    @Override
    default Optional<BomEntity> findByParentUidAndUid(Integer projectUid, Integer uid) {
        return findByProject_UidAndUid(projectUid, uid);
    }
}
