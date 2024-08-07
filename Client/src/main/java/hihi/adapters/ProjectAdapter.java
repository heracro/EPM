package hihi.adapters;

import hihi.application.config.GuiConfig;
import hihi.content.project.ProjectDto;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class ProjectAdapter extends MainResourceAbstractAdapter<ProjectDto> {

    @Override
    protected String getEndpoint() {
        return GuiConfig.API_URL + "/projects";
    }

    @Override
    protected Class<ProjectDto> getDtoClass() {
        return ProjectDto.class;
    }

    @Override
    protected String getEntityName() {
        return "Project";
    }

}
