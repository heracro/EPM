package org.epm.common.service;

import org.epm.common.model.IDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IDependantService<DTO extends IDTO> {

    DTO createEntity(Integer parentUid, DTO dto);

    DTO replaceEntity(Integer parentUid, Integer uid, DTO dto);

    DTO updateEntity(Integer parentUid, Integer uid, DTO dto);

    void deleteEntity(Integer parentUid, Integer uid);

    Page<DTO> findAllByParentUid(Integer parentUid, Pageable pageable);

    DTO findByUidAndParentUid(Integer parentUid, Integer uid);

    String getEntityName();

    String getParentEntityName();
}
