package org.epm.common.model;

import org.mapstruct.MappingTarget;

public interface IMapper<Entity, DTO> {

    DTO toDto(Entity entity);

    Entity toEntity(DTO dto);

   void updateEntityFromDto(DTO dto, @MappingTarget Entity entity);
}
