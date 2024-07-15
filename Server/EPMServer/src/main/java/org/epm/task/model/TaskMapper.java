package org.epm.task.model;

import org.epm.common.model.IMapper;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaskMapper extends IMapper<TaskEntity, TaskDTO> {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    @Override
    TaskDTO toDto(TaskEntity entity);

    @Override
    TaskEntity toEntity(TaskDTO dto);

    @Override
    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(TaskDTO dto, @MappingTarget TaskEntity entity);
}
