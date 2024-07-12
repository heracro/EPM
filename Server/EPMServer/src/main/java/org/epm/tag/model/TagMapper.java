package org.epm.tag.model;

import org.epm.common.model.IMapper;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TagMapper extends IMapper<TagEntity, TagDTO> {

    TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);

    @Override
    TagDTO toDto(TagEntity entity);

    @Override
    TagEntity toEntity(TagDTO dto);

    @Override
    @Mapping(target = "privateId", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(TagDTO dto, @MappingTarget TagEntity entity);
}
