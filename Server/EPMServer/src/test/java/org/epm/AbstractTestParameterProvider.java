package org.epm;

import org.epm.common.model.IDTO;
import org.epm.common.model.IEntity;
import org.epm.common.model.IMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public abstract class AbstractTestParameterProvider
        <Entity extends IEntity, DTO extends IDTO> {

    protected abstract int getDTOAttrCount();

    protected abstract IMapper<Entity, DTO> getMapper();

    protected abstract Entity randomValidEntity();

    protected abstract DTO emptyDTO();

    protected abstract DTO provideSingleAttribute(DTO dto, int caseNumber);

    protected abstract DTO breakSingleAttribute(DTO dto, int caseNumber);

    public Stream<DTO> provideFewDTOsWhichAreValidEntity() {
        List<DTO> list = new ArrayList<>();
        for (int i = 0; i < 3; ++i) {
            list.add(getMapper().toDto(randomValidEntity()));
        }
        return list.stream();
    }

    public Stream<DTO> provideFewDTOsWhichAreInvalidEntity() {
        List<DTO> list = new ArrayList<>();
        for (int i = 0; i < getDTOAttrCount(); ++i) {
            list.add(
                    breakSingleAttribute(getMapper().toDto(randomValidEntity()), i));
        }
        return list.stream();
    }

    public Stream<DTO> provideDTOsWithSingleValidAttribute() {
        List<DTO> list = new ArrayList<>();
        for (int i = 0; i < getDTOAttrCount(); ++i) {
            list.add(provideSingleAttribute(emptyDTO(), i));
        }
        return list.stream();
    }

    public Stream<DTO> provideDTOsWithSingleInvalidAttribute() {
        List<DTO> list = new ArrayList<>();
        for(int i = 0; i < getDTOAttrCount(); ++i) {
            list.add(breakSingleAttribute(emptyDTO(), i));
        }
        return list.stream();
    }
}
