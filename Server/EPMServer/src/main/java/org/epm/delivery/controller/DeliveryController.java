package org.epm.delivery.controller;

import lombok.extern.slf4j.Slf4j;
import org.epm.common.controller.AbstractRestController;
import org.epm.delivery.model.DeliveryDTO;
import org.epm.delivery.service.DeliveryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/deliveries")
public class DeliveryController extends AbstractRestController<DeliveryDTO> {

    public DeliveryController(DeliveryService entityService) {
        super(entityService);
    }

    @Override
    public String getMapping() {
        return "/deliveries";
    }
}
