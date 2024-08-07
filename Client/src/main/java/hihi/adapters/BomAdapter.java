package hihi.adapters;

import hihi.content.bom.BomDto;

public class BomAdapter extends DependantResourceAbstractAdapter<BomDto> {

    @Override
    protected String getEndpoint(Integer parentUid) {
        return "/projects/" + parentUid + "/boms";
    }

    @Override
    protected Class<BomDto> getDtoClass() {
        return BomDto.class;
    }

    @Override
    protected String getEntityName() {
        return "Bom";
    }

}
