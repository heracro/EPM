package org.epm.material;

import org.epm.GenericMainResourcesControllerTest;
import org.epm.material.controller.MaterialController;
import org.epm.material.model.MaterialDTO;
import org.epm.material.model.MaterialEntity;
import org.epm.material.model.MaterialMapper;
import org.epm.material.repository.MaterialRepository;
import org.epm.material.service.MaterialService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MaterialControllerTest
        extends GenericMainResourcesControllerTest<MaterialEntity, MaterialDTO> {

    @Autowired
    private MaterialController materialController;
    @Autowired
    private MaterialService materialService;
    @Autowired
    private MaterialRepository materialRepository;
    @Autowired
    private MaterialTestParameterProvider materialTestParameterProvider;
    @Autowired
    private MaterialMapper materialMapper;

    @Override
    protected MaterialTestParameterProvider getTestParameterProvider() {
        return materialTestParameterProvider;
    }

    @Override
    protected String getMapping() {
        return "/materials";
    }

}
