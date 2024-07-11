package org.epm.material.repository;

import org.epm.common.repository.IRepository;
import org.epm.material.model.MaterialEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialRepository extends IRepository<MaterialEntity> {

    List<MaterialEntity> findTop10ByOrderByFreeQty();
}
