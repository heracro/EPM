package org.epm.common.model;

public interface IMapper<Entity, DTO> {

    DTO toDto(Entity entity);

    Entity toEntity(DTO dto);

    void updateEntityFromDto(DTO dto, Entity entity);
}
