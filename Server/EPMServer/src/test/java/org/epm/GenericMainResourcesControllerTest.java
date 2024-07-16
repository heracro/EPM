package org.epm;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletContext;
import org.epm.common.model.AbstractModuleData;
import org.epm.common.model.IDTO;
import org.epm.common.model.IEntity;
import org.epm.material.controller.MaterialController;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
public abstract class GenericMainResourcesControllerTest<Entity extends IEntity, DTO extends AbstractModuleData & IDTO> {

    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    protected WebApplicationContext webApplicationContext;
    @Autowired
    private DatabaseSeeder databaseSeeder;

    protected abstract AbstractMainTestParameterProvider<Entity, DTO> getTestParameterProvider();
    protected abstract String getMapping();

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

    private Integer provideUidOfExistingEntity() {
        return getTestParameterProvider().provideUidOfExistingEntity();
    }

    private Integer provideUidOfInvalidEntity() {
        return getTestParameterProvider().provideUidOfInvalidEntity();
    }

    private Integer provideUidOfUnconstrainedEntity() {
        return getTestParameterProvider().provideUidOfUnconstrainedEntity();
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
        String response = mockMvc.perform(post(getMapping())
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
        mockMvc.perform(post(getMapping())
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest());
    }

    @ParameterizedTest
    @MethodSource("provideDTOsWithSingleValidAttribute")
    @Order(2)
    void testUpdateEntityWithSingleValidParameter(DTO dto) {
        assertNull(dto, "Test not yet implemented");
    }

    @ParameterizedTest
    @MethodSource("provideDTOsWithSingleInvalidAttribute")
    @Order(2)
    void testUpdateEntityWithSingleInvalidParameter(DTO dto) {
        assertNull(dto, "Test not yet implemented");
    }

    @ParameterizedTest
    @MethodSource("provideFewDTOsWhichAreValidEntity")
    @Order(2)
    void testReplaceWithValidEntity(DTO dto) throws Exception {
        assertNotNull(dto, "ParameterProvider delivered invalid dto (null)");
        String json = objectMapper.writeValueAsString(dto);
        Integer existingUid = provideUidOfExistingEntity();
        String response = mockMvc.perform(put(getMapping() + "/" + existingUid)
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
        Integer existingUid = provideUidOfExistingEntity();
        mockMvc.perform(put(getMapping() + "/" + existingUid)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Order(3)
    void testFindAll() throws Exception {
        mockMvc.perform(get(getMapping()))
                .andExpect(status().isOk());
    }

    @Test
    @Order(3)
    void testFindByUidExistingEntity() throws Exception {
        Integer existingUid = provideUidOfExistingEntity();
        mockMvc.perform(get(getMapping() + "/" + existingUid))
                .andExpect(status().isOk());
    }

    @Test
    @Order(3)
    void testFindByIdNonExistingEntity() throws Exception {
        Integer nonExistingUid = provideUidOfInvalidEntity();
        mockMvc.perform(get(getMapping() + "/" + nonExistingUid))
                .andExpect(status().isNotFound());
    }

    @Test
    @Order(4)
    void testDeleteExistingEntity() throws Exception {
        Integer unconstrainedUid = provideUidOfUnconstrainedEntity();
        mockMvc.perform(delete(getMapping() + "/" + unconstrainedUid))
                .andExpect(status().isNoContent());
    }

    @Test
    @Order(4)
    void testDeleteNonExistingEntity() throws Exception {
        Integer nonExistingUid = provideUidOfInvalidEntity();
        mockMvc.perform(delete(getMapping() + "/" + nonExistingUid))
                .andExpect(status().isNotFound());
    }
}
