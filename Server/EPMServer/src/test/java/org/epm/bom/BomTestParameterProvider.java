package org.epm.bom;

import org.epm.AbstractDependantTestParameterProvider;
import org.epm.bom.model.BomDTO;
import org.epm.bom.model.BomEntity;
import org.epm.bom.model.BomMapper;
import org.epm.bom.repository.BomRepository;
import org.epm.project.model.ProjectEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class BomTestParameterProvider
        extends AbstractDependantTestParameterProvider<BomEntity, ProjectEntity, BomDTO> {

//    @Autowired
//    private MaterialRepository materialRepository;
//    @Autowired
//    private MaterialMapper materialMapper;
//    @Autowired
//    private ProjectRepository projectRepository;
//    @Autowired
//    private ProjectMapper projectMapper;
    @Autowired
    private BomMapper mapper;

    @Override
    protected int getDTOAttrCount() {
        return 0;
    }

    @Override
    protected BomMapper getMapper() {
        return mapper;
    }

    @Override
    protected BomRepository getRepository() {
        return null;
    }

    @Override
    protected Integer provideUidOfExistingEntity() {
        return 0;
    }

    @Override
    protected Integer provideUidOfInvalidEntity() {
        return 0;
    }

    @Override
    protected Integer provideUidOfUnconstrainedEntity() {
        return 0;
    }

    @Override
    protected Stream<BomDTO> provideFewDTOsWhichAreValidEntity() {
        return Stream.empty();
    }

    @Override
    protected Stream<BomDTO> provideFewDTOsWhichAreInvalidEntity() {
        return Stream.empty();
    }

    @Override
    protected Stream<BomDTO> provideDTOsWithSingleValidAttribute() {
        return Stream.empty();
    }

    @Override
    protected Stream<BomDTO> provideDTOsWithSingleInvalidAttribute() {
        return Stream.empty();
    }

    @Override
    protected Integer getValidParentUid() {
        return 0;
    }

}
