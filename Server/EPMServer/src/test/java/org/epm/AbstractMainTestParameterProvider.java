package org.epm;

import org.epm.common.model.IDTO;
import org.epm.common.model.IEntity;

import java.util.stream.Stream;

public abstract class AbstractMainTestParameterProvider
        <Entity extends IEntity, DTO extends IDTO> {

    protected final int RUN_COUNT = 10;

    protected abstract Integer provideUidOfExistingEntity();

    protected abstract Integer provideUidOfInvalidEntity();

    protected abstract Integer provideUidOfUnconstrainedEntity();

    protected abstract Stream<DTO> provideFewDTOsWhichAreValidEntity();

    protected abstract Stream<DTO> provideFewDTOsWhichAreInvalidEntity();

    protected abstract Stream<DTO> provideDTOsWithSingleValidAttribute();

    protected abstract Stream<DTO> provideDTOsWithSingleInvalidAttribute();

}
