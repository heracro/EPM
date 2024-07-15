package org.epm.shop.controller;

import lombok.RequiredArgsConstructor;
import org.epm.common.controller.AbstractRestController;
import org.epm.shop.model.ShopDTO;
import org.epm.shop.service.ShopService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shops")
@RequiredArgsConstructor
public class ShopController extends AbstractRestController<ShopDTO> {

    private final ShopService shopService;

    @Override
    public ShopService getEntityService() {
        return shopService;
    }

}
