package org.epm.delivery;

import org.epm.ITestParameterProvider;
import org.epm.delivery.model.DeliveryDTO;
import org.epm.delivery.model.DeliveryEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class DeliveryTestParameterProvider implements ITestParameterProvider<DeliveryEntity, DeliveryDTO> {
    @Override
    public DeliveryDTO createRandomValidDTO() {
        return null;
    }

    @Override
    public Stream<DeliveryDTO> createDTOWhichIsValidEntity() {
        DeliveryDTO dto = DeliveryDTO.randomInstance();
        return Stream.empty();
    }

    @Override
    public Stream<DeliveryDTO> createDTOWhichIsInvalidEntity() {
        return Stream.empty();
    }
}
