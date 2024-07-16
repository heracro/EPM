package org.epm.delivery;

import org.epm.AbstractMainTestParameterProvider;
import org.epm.common.repository.IRepository;
import org.epm.delivery.model.DeliveryDTO;
import org.epm.delivery.model.DeliveryEntity;
import org.epm.delivery.model.DeliveryMapper;
import org.epm.material.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class DeliveryTestParameterProvider
        extends AbstractMainTestParameterProvider<DeliveryEntity, DeliveryDTO> {

    @Autowired
    private DeliveryMapper mapper;
    @Autowired
    private MaterialRepository materialRepository;
    @Autowired
    private DeliveryRandomizer randomizer;

    @Override
    protected int getDTOAttrCount() {
        return 8;
    }

    @Override
    protected DeliveryMapper getMapper() {
        return mapper;
    }

    @Override
    protected IRepository<DeliveryEntity> getRepository() {
        return null;
    }

    @Override
    protected Integer provideUidOfExistingEntity() {
        return 0;
    }

    @Override
    protected Integer provideUidOfInvalidEntity() {
        return 0;
    }

    @Override
    protected Integer provideUidOfUnconstrainedEntity() {
        return 0;
    }

    @Override
    protected Stream<DeliveryDTO> provideFewDTOsWhichAreValidEntity() {
        return Stream.empty();
    }

    @Override
    protected Stream<DeliveryDTO> provideFewDTOsWhichAreInvalidEntity() {
        return Stream.empty();
    }

    @Override
    protected Stream<DeliveryDTO> provideDTOsWithSingleValidAttribute() {
        return Stream.empty();
    }

    @Override
    protected Stream<DeliveryDTO> provideDTOsWithSingleInvalidAttribute() {
        return Stream.empty();
    }

}
