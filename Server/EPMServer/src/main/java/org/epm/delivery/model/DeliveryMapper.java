package org.epm.delivery.model;

import org.epm.common.model.IMapper;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DeliveryMapper extends IMapper<DeliveryEntity, DeliveryDTO> {

    DeliveryMapper INSTANCE = Mappers.getMapper(DeliveryMapper.class);

    @Override
    DeliveryDTO toDto(DeliveryEntity deliveryEntity);

    @Override
    DeliveryEntity toEntity(DeliveryDTO deliveryDTO);

    @Override
    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(DeliveryDTO deliveryDTO, @MappingTarget DeliveryEntity deliveryEntity);
}
