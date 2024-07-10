package org.epm;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletContext;
import org.epm.common.controller.AbstractEntityController;
import org.epm.common.model.IDTO;
import org.epm.common.model.IEntity;
import org.epm.common.model.IMapper;
import org.epm.common.repository.IRepository;
import org.epm.common.service.AbstractEntityService;
import org.epm.common.utils.DatabaseSeeder;
import org.epm.material.MaterialController;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public abstract class GenericControllerTest<Entity extends IEntity, DTO extends IDTO> {

    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    protected WebApplicationContext webApplicationContext;
    @Autowired
    private DatabaseSeeder databaseSeeder;

    protected abstract IMapper<Entity, DTO> getMapper();
    protected abstract ITestParameterProvider<Entity, DTO> getTestParameterProvider();
    protected abstract AbstractEntityController<DTO> getController();
    protected abstract AbstractEntityService<Entity, DTO> getService();
    protected abstract IRepository<Entity> getRepository();

    private Stream<DTO> createDTOWhichIsValidEntity() {
        return getTestParameterProvider().createDTOWhichIsValidEntity();
    }

    private Stream<DTO> createDTOWhichIsInvalidEntity() {
        return getTestParameterProvider().createDTOWhichIsInvalidEntity();
    }

    private Stream<DTO> singleParameterProvider() {
        return getTestParameterProvider().singleParameterProvider();
    }

    private Stream<DTO> mutateDTOWithSingleInvalidValue() {
        return getTestParameterProvider().mutateDTOWithSingleInvalidValue();
    }


    @AfterAll
    void cleanUp() {
        databaseSeeder.clear();
    }

    @BeforeEach
    void setupEach() {
        databaseSeeder.seed();
    }

    @Test
    @Order(0)
    public void testContextIsInitialized() {
        ServletContext servletContext = webApplicationContext.getServletContext();
        assertNotNull(servletContext, "Servlet context is null");
        assertInstanceOf(MockServletContext.class, servletContext,
                "Servlet context is not instance of MockServletContext");
        assertNotNull(webApplicationContext.getBean(MaterialController.class),
                "Retrieving bean 'MaterialController' from WebAppContext returned nothing");
    }

    @ParameterizedTest
    @MethodSource("createDTOWhichIsValidEntity")
    @Order(1)
    void testCreateValidEntity(DTO dto) throws Exception {
        assertNotNull(dto, "ParameterProvider delivered invalid dto (null)");
        String json = objectMapper.writeValueAsString(dto);
        String response = mockMvc.perform(post(getController().getMapping())
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();
        DTO responseObject = objectMapper.readValue(response, new TypeReference<>() {});
        assertEquals(responseObject, dto, "Response object is different than request object");
    }

    @ParameterizedTest
    @MethodSource("createDTOWhichIsInvalidEntity")
    @Order(1)
    void testCreateInvalidEntityAndFail(DTO dto) throws Exception {
        assertNotNull(dto, "ParameterProvider delivered invalid dto (null)");
        String json = objectMapper.writeValueAsString(dto);
        Entity toBeUpdated = getRepository().findFirstByOrderByIdAsc();
        mockMvc.perform(put(getController().getMapping() + "/" + toBeUpdated.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest());
    }

    @ParameterizedTest
    @MethodSource("singleParameterProvider")
    @Order(2)
    void testUpdateEntityWithSingleValidParameter(DTO dto) {

    }

    @ParameterizedTest
    @MethodSource("mutateDTOWithSingleInvalidValue")
    @Order(2)
    void testUpdateEntityWithSingleInvalidParameter(DTO dto) {

    }

}
