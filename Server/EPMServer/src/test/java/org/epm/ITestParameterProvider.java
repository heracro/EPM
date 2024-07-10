package org.epm;

import org.epm.common.model.IDTO;
import org.epm.common.model.IEntity;

import java.util.stream.Stream;

public interface ITestParameterProvider<Entity extends IEntity, DTO extends IDTO> {

    DTO createRandomValidDTO() throws Exception;

    Stream<DTO> createDTOWhichIsValidEntity();

    Stream<DTO> createDTOWhichIsInvalidEntity();

    Stream<DTO> singleParameterProvider();

    Stream<DTO> mutateDTOWithSingleInvalidValue();
}
