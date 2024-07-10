package org.epm.bom;

import org.epm.bom.model.BomEntity;
import org.epm.common.repository.IRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BomRepository extends IRepository<BomEntity> {
}
