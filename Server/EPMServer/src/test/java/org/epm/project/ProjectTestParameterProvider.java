package org.epm.project;

import org.epm.AbstractTestParameterProvider;
import org.epm.common.model.IMapper;
import org.epm.project.model.ProjectDTO;
import org.epm.project.model.ProjectEntity;
import org.springframework.stereotype.Component;

@Component
public class ProjectTestParameterProvider
        extends AbstractTestParameterProvider<ProjectEntity, ProjectDTO> {

    @Override
    protected int getDTOAttrCount() {
        return 0;
    }

    @Override
    protected IMapper<ProjectEntity, ProjectDTO> getMapper() {
        return null;
    }

    @Override
    protected ProjectEntity randomInstance() {
        return null;
    }

    @Override
    protected ProjectDTO provideSingleAttribute(ProjectDTO projectDTO, int caseNumber) {
        return null;
    }

    @Override
    protected ProjectDTO breakSingleAttribute(ProjectDTO projectDTO, int caseNumber) {
        return null;
    }
}
