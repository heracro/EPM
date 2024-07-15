package org.epm.common.service;

import org.epm.common.model.IDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IService<DTO extends IDTO> {

    DTO createEntity(DTO dto);

    DTO replaceEntity(Integer uid, DTO dto);

    DTO updateEntity(Integer uid, DTO dto);

    void deleteEntity(Integer uid);

    Page<DTO> findAll(Pageable pageable);

    DTO findByUid(Integer uid);

    String getEntityName();

}
