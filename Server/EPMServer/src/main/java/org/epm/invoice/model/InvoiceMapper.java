package org.epm.invoice.model;

import org.epm.common.model.IMapper;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface InvoiceMapper extends IMapper<InvoiceEntity, InvoiceDTO> {

    InvoiceMapper INSTANCE = Mappers.getMapper(InvoiceMapper.class);

    @Override
    InvoiceDTO toDto(InvoiceEntity entity);

    @Override
    InvoiceEntity toEntity(InvoiceDTO dto);

    @Override
    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(InvoiceDTO dto, @MappingTarget InvoiceEntity entity);
}
