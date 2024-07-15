package org.epm.delivery.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.epm.common.controller.AbstractRestController;
import org.epm.delivery.model.DeliveryDTO;
import org.epm.delivery.service.DeliveryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/deliveries")
@RequiredArgsConstructor
public class DeliveryController extends AbstractRestController<DeliveryDTO> {

    private final DeliveryService deliveryService;

    @Override
    public DeliveryService getEntityService() {
        return deliveryService;
    }

}
