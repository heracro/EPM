package hihi.adapters;

import hihi.content.changelog.ChangeLogDto;

public class ChangeLogAdapter extends DependantResourceAbstractAdapter<ChangeLogDto> {
    @Override
    protected String getEndpoint(Integer parentUid) {
        return "/projects/" + parentUid + "/changelogs";
    }

    @Override
    protected Class<ChangeLogDto> getDtoClass() {
        return ChangeLogDto.class;
    }

    @Override
    protected String getEntityName() {
        return "Change Log";
    }
}
