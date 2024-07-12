package org.epm.changeLog.model;

import org.epm.common.model.IMapper;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ChangeLogMapper extends IMapper<ChangeLogEntity, ChangeLogDTO> {

    @Override
    ChangeLogDTO toDto(ChangeLogEntity entity);

    @Override
    ChangeLogEntity toEntity(ChangeLogDTO dto);

    @Override
    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(ChangeLogDTO dto, @MappingTarget ChangeLogEntity entity);
}
