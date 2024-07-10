package org.epm.project;

import org.epm.ITestParameterProvider;
import org.epm.project.model.ProjectDTO;
import org.epm.project.model.ProjectEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class ProjectTestParameterProvider
        implements ITestParameterProvider<ProjectEntity, ProjectDTO> {
    @Override
    public ProjectDTO createRandomValidDTO() {
        return null;
    }

    @Override
    public Stream<ProjectDTO> createDTOWhichIsValidEntity() {
        return Stream.empty();
    }

    @Override
    public Stream<ProjectDTO> createDTOWhichIsInvalidEntity() {
        return Stream.empty();
    }
}
