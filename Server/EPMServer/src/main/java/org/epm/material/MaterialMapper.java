package org.epm.material;

import org.epm.material.model.Material;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MaterialMapper {
    MaterialMapper INSTANCE = Mappers.getMapper(MaterialMapper.class);

    @Mapping(target = "id", ignore = true)
    void updateMaterialFromDto(@MappingTarget Material existingMaterial, Material update);
}

