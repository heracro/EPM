package org.epm.common.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IService<DTO> {
    DTO createEntity(DTO dto);
    DTO replaceEntity(Long id, DTO dto);
    Page<DTO> findAll(Pageable pageable);
    DTO findById(Long id);
    DTO updateEntity(Long id, DTO dto);
    void deleteEntity(Long id);
    String getEntityName();
}
