package org.epm.shop.controller;

import org.epm.common.controller.AbstractRestController;
import org.epm.shop.model.ShopDTO;
import org.epm.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shops")
public class ShopController extends AbstractRestController<ShopDTO> {

    @Autowired
    public ShopController(ShopService service) {
        super(service);
    }

    @Override
    public String getMapping() {
        return "/shops";
    }
}
