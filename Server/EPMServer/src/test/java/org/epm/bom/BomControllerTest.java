package org.epm.bom;

import org.epm.GenericDependantResourcesControllerTest;
import org.epm.bom.controller.BomController;
import org.epm.bom.model.BomDTO;
import org.epm.bom.model.BomEntity;
import org.epm.bom.model.BomMapper;
import org.epm.bom.repository.BomRepository;
import org.epm.bom.service.BomService;
import org.epm.material.repository.MaterialRepository;
import org.epm.material.service.MaterialService;
import org.epm.project.model.ProjectEntity;
import org.epm.project.repository.ProjectRepository;
import org.epm.project.service.ProjectService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BomControllerTest
        extends GenericDependantResourcesControllerTest<BomEntity, ProjectEntity, BomDTO> {

    @Autowired
    private BomController bomController;
    @Autowired
    private BomService bomService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private MaterialService materialService;
    @Autowired
    private BomRepository bomRepository;
    @Autowired
    private MaterialRepository materialRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private BomTestParameterProvider bomTestParameterProvider;
    @Autowired
    private BomMapper bomMapper;

    /*
    protected BomController getController() {
        return bomController;
    }

    protected BomService getService() {
        return bomService;
    }

    protected BomRepository getRepository() {
        return bomRepository;
    }

    protected BomMapper getMapper() {
        return bomMapper;
    }
     */

    @Override
    protected BomTestParameterProvider getTestParameterProvider() {
        return bomTestParameterProvider;
    }

    @Override
    protected String getMapping() {
        return "/projects/{uid}/boms";
    }
}
