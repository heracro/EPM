package hihi.adapters;

import hihi.content.shop.ShopDto;

public class ShopAdapter extends MainResourceAbstractAdapter<ShopDto> {
    @Override
    protected String getEndpoint() {
        return "/shops";
    }

    @Override
    protected Class<ShopDto> getDtoClass() {
        return ShopDto.class;
    }

    @Override
    protected String getEntityName() {
        return "Shop";
    }
}
