package org.epm.material.model;

import org.epm.common.model.IMapper;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MaterialMapper extends IMapper<MaterialEntity, MaterialDTO> {

    MaterialMapper INSTANCE = Mappers.getMapper(MaterialMapper.class);

    @Override
    MaterialDTO toDto(MaterialEntity materialEntity);

    @Override
    MaterialEntity toEntity(MaterialDTO materialDTO);

    @Override
    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(MaterialDTO materialDTO, @MappingTarget MaterialEntity materialEntity);
}

