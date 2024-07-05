package org.epm.material;

import org.epm.common.repository.IRepository;
import org.epm.material.model.MaterialEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepository extends IRepository<MaterialEntity> {
}
