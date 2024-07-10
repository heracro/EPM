package org.epm;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
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
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
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
    protected abstract AbstractTestParameterProvider<Entity, DTO> getTestParameterProvider();
    protected abstract AbstractEntityController<DTO> getController();
    protected abstract AbstractEntityService<Entity, DTO> getService();
    protected abstract IRepository<Entity> getRepository();

    private Stream<DTO> provideFewDTOsWhichAreValidEntity() {
        return getTestParameterProvider().provideFewDTOsWhichAreValidEntity();
    }

    private Stream<DTO> provideFewDTOsWhichAreInvalidEntity() {
        return getTestParameterProvider().provideFewDTOsWhichAreInvalidEntity();
    }

    private Stream<DTO> provideDTOsWithSingleValidAttribute() {
        return getTestParameterProvider().provideDTOsWithSingleValidAttribute();
    }

    private Stream<DTO> provideDTOsWithSingleInvalidAttribute() {
        return getTestParameterProvider().provideDTOsWithSingleInvalidAttribute();
    }

    @BeforeAll
    void setup() {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
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
    @MethodSource("provideFewDTOsWhichAreValidEntity")
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
    @MethodSource("provideFewDTOsWhichAreInvalidEntity")
    @Order(1)
    void testCreateInvalidEntityAndFail(DTO dto) throws Exception {
        assertNotNull(dto, "ParameterProvider delivered invalid dto (null)");
        String json = objectMapper.writeValueAsString(dto);
        mockMvc.perform(post(getController().getMapping())
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest());
    }

    @ParameterizedTest
    @MethodSource("provideDTOsWithSingleValidAttribute")
    @Order(2)
    void testUpdateEntityWithSingleValidParameter(DTO dto) {
        assertNotNull(dto);
    }

    @ParameterizedTest
    @MethodSource("provideDTOsWithSingleInvalidAttribute")
    @Order(2)
    void testUpdateEntityWithSingleInvalidParameter(DTO dto) {
        assertNotNull(dto);
    }

    @ParameterizedTest
    @MethodSource("provideFewDTOsWhichAreValidEntity")
    @Order(2)
    void testReplaceWithValidEntity(DTO dto) throws Exception {
        assertNotNull(dto, "ParameterProvider delivered invalid dto (null)");
        String json = objectMapper.writeValueAsString(dto);
        Long firstId = getRepository().findFirstByOrderByIdAsc().getId();
        String response = mockMvc.perform(put(getController().getMapping() + "/" + firstId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        DTO responseObject = objectMapper.readValue(response, new TypeReference<>() {});
        assertEquals(responseObject, dto, "Response object is different than request object");
    }

    @ParameterizedTest
    @MethodSource("provideFewDTOsWhichAreInvalidEntity")
    @Order(2)
    void testReplaceWithInvalidEntity(DTO dto) throws Exception {
        assertNotNull(dto, "ParameterProvider delivered invalid dto (null)");
        String json = objectMapper.writeValueAsString(dto);
        Long firstId = getRepository().findFirstByOrderByIdAsc().getId();
        mockMvc.perform(put(getController().getMapping() + "/" + firstId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Order(3)
    void testFindAll() throws Exception {
        mockMvc.perform(get(getController().getMapping()))
                .andExpect(status().isOk());
    }

    @Test
    @Order(3)
    void testFindByIdExistingEntity() throws Exception {
        long firstId = getRepository().findFirstByOrderByIdAsc().getId();
        mockMvc.perform(get(getController().getMapping() + "/" + firstId))
                .andExpect(status().isOk());
    }

    @Test
    @Order(3)
    void testFindByIdNonExistingEntity() throws Exception {
        long afterLastId = getRepository().findFirstByOrderByIdDesc().getId() + 1;
        mockMvc.perform(get(getController().getMapping() + "/" + afterLastId))
                .andExpect(status().isNotFound());
    }

    @Test
    @Order(4)
    void testDeleteExistingEntity() throws Exception {
        // to have unconstrained entity in DB (i.e. material which does not occur in any Bom nor Delivery), create:
        DTO dto = provideFewDTOsWhichAreValidEntity()
                .findFirst()
                .orElseThrow(NullPointerException::new);
        Entity entity = getMapper().toEntity(dto);
        entity = getRepository().save(entity);
        // and now we are sure we can delete this.
        mockMvc.perform(delete(getController().getMapping() + "/" + entity.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    @Order(4)
    void testDeleteNonExistingEntity() throws Exception {
        long afterLastId = getRepository().findFirstByOrderByIdDesc().getId() + 1;
        mockMvc.perform(delete(getController().getMapping() + "/" + afterLastId))
                .andExpect(status().isNotFound());
    }
}
