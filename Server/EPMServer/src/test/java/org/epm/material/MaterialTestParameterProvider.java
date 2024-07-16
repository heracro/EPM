package org.epm.material;

import org.epm.AbstractMainTestParameterProvider;
import org.epm.material.enums.Unit;
import org.epm.material.model.MaterialDTO;
import org.epm.material.model.MaterialEntity;
import org.epm.material.model.MaterialMapper;
import org.epm.material.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.stream.Stream;

@Component
public class MaterialTestParameterProvider
        extends AbstractMainTestParameterProvider<MaterialEntity, MaterialDTO> {

    @Autowired
    private MaterialMapper mapper;

    @Override
    protected int getDTOAttrCount() {
        return 7;
    }

    @Override
    protected MaterialMapper getMapper() {
        return mapper;
    }

    @Override
    protected MaterialRepository getRepository() {
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
    protected Stream<MaterialDTO> provideFewDTOsWhichAreValidEntity() {
        return Stream.empty();
    }

    @Override
    protected Stream<MaterialDTO> provideFewDTOsWhichAreInvalidEntity() {
        return Stream.empty();
    }

    @Override
    protected Stream<MaterialDTO> provideDTOsWithSingleValidAttribute() {
        return Stream.empty();
    }

    @Override
    protected Stream<MaterialDTO> provideDTOsWithSingleInvalidAttribute() {
        return Stream.empty();
    }

    protected MaterialDTO provideSingleAttribute(MaterialDTO dto, final int caseNumber) {
        if (dto == null) {
            dto = new MaterialDTO();
        }
        Random random = new Random();
        switch (caseNumber % getDTOAttrCount()) {
            case 0 -> dto.setName("Material " + random.nextInt(2000));
            case 1 -> dto.setNorm((random.nextBoolean() ? "DIN" : "ISO") + (random.nextInt(1500) + 500));
            case 2 -> dto.setDimensions(random.nextInt(200) + "x" + random.nextInt(160) + "x" + random.nextInt(40));
            case 3 -> dto.setWeight(random.nextInt(30000) / 100f);
            case 4 -> dto.setTotalQty(random.nextInt(60));
            case 5 -> dto.setFreeQty(random.nextInt(20));
            case 6 -> dto.setUnit(Unit.randomUnit());
        }
        return dto;
    }

    protected MaterialDTO breakSingleAttribute(MaterialDTO dto, int caseNumber) {
        if (dto == null) {
            dto = new MaterialDTO();
        }
        switch (caseNumber % getDTOAttrCount()) {
            case 0 -> dto.setName("N"); //too short
            case 1 -> dto.setNorm("S"); //too short
            case 2 -> dto.setDatasheet("D"); //too short
            case 3 -> dto.setDimensions(""); //cannot be empty
            case 4 -> dto.setWeight(-3.6f);
            case 5 -> dto.setTotalQty(-10);
            case 6 -> dto.setFreeQty(-3);
        }
        return dto;
    }
}
