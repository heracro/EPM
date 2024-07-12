package org.epm.changeLog.model;

import org.epm.common.model.IMapper;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ChangeLogMapper extends IMapper<ChangeLogEntity, ChangeLogDTO> {

    ChangeLogMapper INSTANCE = Mappers.getMapper(ChangeLogMapper.class);

    @Override
    ChangeLogDTO toDto(ChangeLogEntity entity);

    @Override
    ChangeLogEntity toEntity(ChangeLogDTO dto);

    @Override
    @Mapping(target = "privateId", ignore = true)
    @Mapping(target = "action", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(ChangeLogDTO dto, @MappingTarget ChangeLogEntity entity);
}
