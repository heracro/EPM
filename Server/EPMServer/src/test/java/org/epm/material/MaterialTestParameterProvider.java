package org.epm.material;

import org.epm.ITestParameterProvider;
import org.epm.material.model.MaterialDTO;
import org.epm.material.model.MaterialEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class MaterialTestParameterProvider implements ITestParameterProvider<MaterialEntity, MaterialDTO> {

    @Override
    public MaterialDTO createRandomValidDTO() {
        return null;
    }

    @Override
    public Stream<MaterialDTO> createDTOWhichIsValidEntity() {
        return Stream.empty();
    }

    @Override
    public Stream<MaterialDTO> createDTOWhichIsInvalidEntity() {
        return Stream.empty();
    }
}
