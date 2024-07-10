package org.epm.delivery;

import lombok.extern.slf4j.Slf4j;
import org.epm.common.controller.AbstractEntityController;
import org.epm.delivery.model.DeliveryDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/deliveries")
public class DeliveryController extends AbstractEntityController<DeliveryDTO> {

    public DeliveryController(DeliveryService entityService) {
        super(entityService);
    }

    @Override
    public String getMapping() {
        return "/deliveries";
    }
}
