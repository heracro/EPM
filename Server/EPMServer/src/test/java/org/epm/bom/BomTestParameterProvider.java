package org.epm.bom;

import org.epm.AbstractTestParameterProvider;
import org.epm.bom.model.BomDTO;
import org.epm.bom.model.BomEntity;
import org.epm.bom.model.BomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BomTestParameterProvider
        extends AbstractTestParameterProvider<BomEntity, BomDTO> {

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
    protected BomEntity randomInstance() {
        return BomEntity.randomInstance();
    }

    @Override
    protected BomDTO provideSingleAttribute(BomDTO bomDTO, int caseNumber) {
        return bomDTO;
    }

    @Override
    protected BomDTO breakSingleAttribute(BomDTO bomDTO, int caseNumber) {
        return bomDTO;
    }
}
