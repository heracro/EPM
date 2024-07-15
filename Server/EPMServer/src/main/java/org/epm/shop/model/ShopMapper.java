package org.epm.shop.model;

import org.epm.common.model.IMapper;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ShopMapper extends IMapper<ShopEntity, ShopDTO> {

    ShopMapper INSTANCE = Mappers.getMapper(ShopMapper.class);

    @Override
    ShopDTO toDto(ShopEntity entity);

    @Override
    ShopEntity toEntity(ShopDTO dto);

    @Override
    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(ShopDTO dto, @MappingTarget ShopEntity entity);

}
