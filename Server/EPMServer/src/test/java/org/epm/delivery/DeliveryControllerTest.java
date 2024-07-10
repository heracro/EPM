package org.epm.delivery;

import org.epm.GenericControllerTest;
import org.epm.common.model.IMapper;
import org.epm.delivery.model.DeliveryDTO;
import org.epm.delivery.model.DeliveryEntity;
import org.epm.delivery.model.DeliveryMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class DeliveryControllerTest extends GenericControllerTest<DeliveryEntity, DeliveryDTO> {

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
    protected DeliveryController getController() {
        return deliveryController;
    }

    @Override
    protected DeliveryService getService() {
        return deliveryService;
    }

    @Override
    protected DeliveryRepository getRepository() {
        return deliveryRepository;
    }

    @Override
    protected IMapper<DeliveryEntity, DeliveryDTO> getMapper() {
        return deliveryMapper;
    }

    @Override
    protected DeliveryTestParameterProvider getTestParameterProvider() {
        return deliveryTestParameterProvider;
    }
}
