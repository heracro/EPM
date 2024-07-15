package org.epm.shop.service;

import lombok.RequiredArgsConstructor;
import org.epm.common.service.AbstractService;
import org.epm.shop.model.ShopDTO;
import org.epm.shop.model.ShopEntity;
import org.epm.shop.model.ShopMapper;
import org.epm.shop.repository.ShopRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopService extends AbstractService<ShopEntity, ShopDTO> {

    private final ShopRepository shopRepository;
    private final ShopMapper shopMapper;

    @Override
    public ShopMapper getMapper() {
        return shopMapper;
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
