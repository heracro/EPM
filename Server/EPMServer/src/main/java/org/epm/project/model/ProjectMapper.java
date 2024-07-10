package org.epm.project.model;

import org.epm.common.model.IMapper;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProjectMapper extends IMapper<ProjectEntity, ProjectDTO> {

    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    @Override
    ProjectDTO toDto(ProjectEntity projectEntity);

    @Override
    ProjectEntity toEntity(ProjectDTO projectDTO);

    @Override
    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(ProjectDTO projectDTO, @MappingTarget ProjectEntity projectEntity);
}
