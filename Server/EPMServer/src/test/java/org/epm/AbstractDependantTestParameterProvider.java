package org.epm;

import org.epm.common.model.IDTO;
import org.epm.common.model.IDependantEntity;
import org.epm.common.model.IEntity;
import org.epm.common.model.IMapper;
import org.epm.common.repository.IDependantRepository;

import java.util.stream.Stream;

public abstract class AbstractDependantTestParameterProvider
        <Entity extends IDependantEntity<ParentEntity>, ParentEntity extends IEntity, DTO extends IDTO> {

    protected abstract int getDTOAttrCount();

    protected abstract IMapper<Entity, DTO> getMapper();

    protected abstract IDependantRepository<Entity> getRepository();

    protected abstract Integer provideUidOfExistingEntity();

    protected abstract Integer provideUidOfInvalidEntity();

    protected abstract Integer provideUidOfUnconstrainedEntity();

    protected abstract Stream<DTO> provideFewDTOsWhichAreValidEntity();

    protected abstract Stream<DTO> provideFewDTOsWhichAreInvalidEntity();

    protected abstract Stream<DTO> provideDTOsWithSingleValidAttribute();

    protected abstract Stream<DTO> provideDTOsWithSingleInvalidAttribute();

    protected abstract Integer getValidParentUid();
}
