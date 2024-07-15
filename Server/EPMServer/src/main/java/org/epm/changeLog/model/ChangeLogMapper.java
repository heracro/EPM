package org.epm.changeLog.model;

import org.epm.common.model.IMapper;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ChangeLogMapper extends IMapper<ChangeLogEntity, ChangeLogDTO> {

    ChangeLogMapper INSTANCE = Mappers.getMapper(ChangeLogMapper.class);

    @Override
    @Mapping(target = "action", ignore = true)
    ChangeLogDTO toDto(ChangeLogEntity entity);

    @Override
    @Mapping(target = "id", ignore = true)
    ChangeLogEntity toEntity(ChangeLogDTO dto);

    @Override
    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(ChangeLogDTO dto, @MappingTarget ChangeLogEntity entity);
}
