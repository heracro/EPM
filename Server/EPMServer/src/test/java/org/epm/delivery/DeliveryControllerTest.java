package org.epm.delivery;

import org.epm.GenericMainResourcesControllerTest;
import org.epm.delivery.controller.DeliveryController;
import org.epm.delivery.model.DeliveryDTO;
import org.epm.delivery.model.DeliveryEntity;
import org.epm.delivery.model.DeliveryMapper;
import org.epm.delivery.repository.DeliveryRepository;
import org.epm.delivery.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;

public class DeliveryControllerTest extends GenericMainResourcesControllerTest<DeliveryEntity, DeliveryDTO> {

    @Autowired
    private DeliveryController deliveryController;
    @Autowired
    private DeliveryService deliveryService;
    @Autowired
    private DeliveryRepository deliveryRepository;
    @Autowired
    private DeliveryTestParameterProvider deliveryTestParameterProvider;
    @Autowired
    private DeliveryMapper deliveryMapper;

    @Override
    protected DeliveryTestParameterProvider getTestParameterProvider() {
        return deliveryTestParameterProvider;
    }

    @Override
    protected String getMapping() {
        return "/deliveries";
    }

    @Override
    protected DeliveryDTO deserializeDTO(String json) throws Exception {
        return objectMapper.readValue(json, DeliveryDTO.class);
    }


}
