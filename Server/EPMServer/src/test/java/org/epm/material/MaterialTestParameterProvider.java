package org.epm.material;

import org.epm.AbstractTestParameterProvider;
import org.epm.material.model.MaterialDTO;
import org.epm.material.model.MaterialEntity;
import org.epm.material.model.MaterialMapper;
import org.epm.material.model.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class MaterialTestParameterProvider
        extends AbstractTestParameterProvider<MaterialEntity, MaterialDTO> {

    @Autowired
    private MaterialMapper mapper;

    public static int DTO_ATTR_COUNT = 7;

    @Override
    protected int getDTOAttrCount() {
        return DTO_ATTR_COUNT;
    }

    @Override
    protected MaterialMapper getMapper() {
        return mapper;
    }

    @Override
    protected MaterialEntity randomInstance() {
        return MaterialEntity.randomInstance();
    }

    @Override
    protected MaterialDTO emptyInstance() {
        return MaterialDTO.emptyInstance();
    }

    @Override
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

    @Override
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
