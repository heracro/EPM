package org.epm.shop.service;

import org.epm.common.service.AbstractService;
import org.epm.shop.model.ShopDTO;
import org.epm.shop.model.ShopEntity;
import org.epm.shop.model.ShopMapper;
import org.epm.shop.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopService extends AbstractService<ShopEntity, ShopDTO> {

    private final ShopRepository shopRepository;

    @Autowired
    public ShopService(ShopRepository shopRepository, ShopMapper shopMapper) {
        super(shopMapper);
        this.shopRepository = shopRepository;
    }

    @Override
    protected ShopRepository getRepository() {
        return shopRepository;
    }

    @Override
    public String getEntityName() {
        return "Shop";
    }
}
