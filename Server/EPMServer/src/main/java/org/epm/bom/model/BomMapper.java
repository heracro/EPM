package org.epm.bom.model;

import org.epm.common.model.IMapper;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BomMapper extends IMapper<BomEntity, BomDTO> {

    BomMapper INSTANCE = Mappers.getMapper(BomMapper.class);

    @Override
    @Mapping(source = "material.uid", target = "materialUid")
    @Mapping(source = "project.uid", target = "projectUid")
    BomDTO toDto(BomEntity bomEntity);

    @Override
    @Mapping(source = "materialUid", target = "material.uid")
    @Mapping(source = "projectUid", target = "project.uid")
    BomEntity toEntity(BomDTO bomDTO);

    @Override
    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(BomDTO bomDTO, @MappingTarget BomEntity bomEntity);
}
