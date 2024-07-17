package org.epm.material;

import org.epm.GenericMainResourcesControllerTest;
import org.epm.material.model.MaterialDTO;
import org.epm.material.model.MaterialEntity;
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
    private MaterialTestParameterProvider materialTestParameterProvider;

    @Override
    protected MaterialTestParameterProvider getTestParameterProvider() {
        return materialTestParameterProvider;
    }

    @Override
    protected String getMapping() {
        return "/materials";
    }

    @Override
    protected MaterialDTO deserializeDTO(String json) throws Exception {
        return objectMapper.readValue(json, MaterialDTO.class);
    }

}
