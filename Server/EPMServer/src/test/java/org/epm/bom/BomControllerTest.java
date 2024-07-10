package org.epm.bom;

import org.epm.GenericControllerTest;
import org.epm.bom.model.BomDTO;
import org.epm.bom.model.BomEntity;
import org.epm.bom.model.BomMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BomControllerTest
        extends GenericControllerTest<BomEntity, BomDTO> {

    @Autowired
    private BomController bomController;
    @Autowired
    private BomService bomService;
    @Autowired
    private BomRepository bomRepository;
    @Autowired
    private BomTestParameterProvider bomTestParameterProvider;
    @Autowired
    private BomMapper bomMapper;

    @Override
    protected BomController getController() {
        return bomController;
    }

    @Override
    protected BomService getService() {
        return bomService;
    }

    @Override
    protected BomRepository getRepository() {
        return bomRepository;
    }

    @Override
    protected BomMapper getMapper() {
        return bomMapper;
    }

    @Override
    protected BomTestParameterProvider getTestParameterProvider() {
        return bomTestParameterProvider;
    }
}
