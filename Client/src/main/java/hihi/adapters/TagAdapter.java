package hihi.adapters;

import hihi.content.tag.TagDto;

public class TagAdapter extends MainResourceAbstractAdapter<TagDto> {
    @Override
    protected String getEndpoint() {
        return "/tags";
    }

    @Override
    protected Class<TagDto> getDtoClass() {
        return TagDto.class;
    }

    @Override
    protected String getEntityName() {
        return "Tag";
    }
}
