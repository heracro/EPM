package org.epm.bom.service;

import org.epm.bom.model.BomDTO;
import org.epm.bom.model.BomEntity;
import org.epm.bom.model.BomMapper;
import org.epm.bom.repository.BomRepository;
import org.epm.common.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BomService extends AbstractService<BomEntity, BomDTO> {

    private final BomRepository bomRepository;

    @Autowired
    public BomService(BomRepository bomRepository, BomMapper mapper) {
        super(mapper);
        this.bomRepository = bomRepository;
    }

    @Override
    protected BomRepository getRepository() {
        return bomRepository;
    }

    @Override
    public String getEntityName() {
        return "Bom";
    }
}
