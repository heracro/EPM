package org.epm;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletContext;
import lombok.extern.slf4j.Slf4j;
import org.epm.common.model.IDTO;
import org.epm.common.model.IEntity;
import org.epm.common.utils.FontColor;
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

import static org.epm.common.utils.ConsoleStringUtils.fontColor;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
public abstract class GenericMainResourcesControllerTest<Entity extends IEntity, DTO extends IDTO> {

    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    public WebApplicationContext webApplicationContext;
    @Autowired
    private DatabaseSeeder databaseSeeder;

    protected abstract AbstractMainTestParameterProvider<Entity, DTO> getTestParameterProvider();
    protected abstract String getMapping();
    protected abstract DTO deserializeDTO(String json) throws Exception;

    private void info(String format, Object... args) {
        log.info(fontColor(FontColor.BRIGHT_RED, format, args));
    }

    private Stream<DTO> provideFewDTOsWhichAreValidEntity() {
        info("provideFewDTOsWhichAreValidEntity()");
        return getTestParameterProvider().provideFewDTOsWhichAreValidEntity();
    }

    private Stream<DTO> provideFewDTOsWhichAreInvalidEntity() {
        info("provideFewDTOsWhichAreInvalidEntity()");
        return getTestParameterProvider().provideFewDTOsWhichAreInvalidEntity();
    }

    private Stream<DTO> provideDTOsWithSingleValidAttribute() {
        info("provideDTOsWithSingleValidAttribute()");
        return getTestParameterProvider().provideDTOsWithSingleValidAttribute();
    }

    private Stream<DTO> provideDTOsWithSingleInvalidAttribute() {
        info("provideDTOsWithSingleInvalidAttribute()");
        return getTestParameterProvider().provideDTOsWithSingleInvalidAttribute();
    }

    private Integer provideUidOfExistingEntity() {
        info("provideUidOfExistingEntity()");
        return getTestParameterProvider().provideUidOfExistingEntity();
    }

    private Integer provideUidOfInvalidEntity() {
        info("provideUidOfInvalidEntity()");
        return getTestParameterProvider().provideUidOfInvalidEntity();
    }

    private Integer provideUidOfUnconstrainedEntity() {
        info("provideUidOfUnconstrainedEntity()");
        return getTestParameterProvider().provideUidOfUnconstrainedEntity();
    }

    @BeforeAll
    void setup() {
        info("@BeforeAll: setup()");
        databaseSeeder.clear();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @BeforeEach
    void setupEach() {
        info("@BeforeEach: setupEach()");
        databaseSeeder.seed();
    }

    @Test
    @Order(0)
    public void testContextIsInitialized() {
        info("testContextIsInitialized()");
        ServletContext servletContext = webApplicationContext.getServletContext();
        assertNotNull(servletContext, "Servlet context is null");
        assertInstanceOf(MockServletContext.class, servletContext,
                "Servlet context is not instance of MockServletContext");
        assertNotNull(webApplicationContext.getBean(MaterialController.class),
                "Retrieving bean 'MaterialController' from WebAppContext returned nothing");
        info("testContextIsInitialized() - SUCCESS");
    }

    @ParameterizedTest
    @MethodSource("provideFewDTOsWhichAreValidEntity")
    @Order(1)
    void testCreateValidEntity(DTO dto) throws Exception {
        info("testCreateValidEntity({})", dto);
        assertNotNull(dto, "ParameterProvider delivered invalid dto (null)");
        String json = objectMapper.writeValueAsString(dto);
        String response = mockMvc.perform(post(getMapping())
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();
        DTO responseObject = deserializeDTO(response);
        assertEquals(responseObject, dto, "Response object is different than request object");
    }

    @ParameterizedTest
    @MethodSource("provideFewDTOsWhichAreInvalidEntity")
    @Order(1)
    void testCreateInvalidEntityAndFail(DTO dto) throws Exception {
        info("testCreateInvalidEntityAndFail({})", dto);
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
    void testUpdateEntityWithSingleValidParameter(DTO dto) throws Exception {
        info("testUpdateEntityWithSingleValidParameter({})", dto);
        assertNotNull(dto, "ParameterProvider delivered invalid dto (null)");
        String json = objectMapper.writeValueAsString(dto);
        mockMvc.perform(patch(getMapping() + "/" + provideUidOfExistingEntity())
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
    }

    @ParameterizedTest 
    @MethodSource("provideDTOsWithSingleInvalidAttribute")
    @Order(2)
    void testUpdateEntityWithSingleInvalidParameter(DTO dto) throws Exception {
        info("testUpdateEntityWithSingleValidParameter({})", dto);
        assertNotNull(dto, "ParameterProvider delivered invalid dto (null)");
        String json = objectMapper.writeValueAsString(dto);
        mockMvc.perform(patch(getMapping() + "/" + provideUidOfExistingEntity())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @ParameterizedTest 
    @MethodSource("provideDTOsWithSingleValidAttribute")
    @Order(2)
    void testUpdateNotExistingEntityWithSingleValidParameter(DTO dto) throws Exception {
        info("testUpdateEntityWithSingleValidParameter({})", dto);
        assertNotNull(dto, "ParameterProvider delivered invalid dto (null)");
        String json = objectMapper.writeValueAsString(dto);
        mockMvc.perform(patch(getMapping() + "/" + provideUidOfInvalidEntity())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound());
    }

    @ParameterizedTest 
    @MethodSource("provideFewDTOsWhichAreValidEntity")
    @Order(2)
    void testReplaceWithValidEntity(DTO dto) throws Exception {
        info("testReplaceWithValidEntity({})", dto);
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
        DTO responseObject = deserializeDTO(response);
        assertEquals(responseObject, dto, "Response object is different than request object");
    }

    @ParameterizedTest //NOK!
    @MethodSource("provideFewDTOsWhichAreInvalidEntity")
    @Order(2)
    void testReplaceWithInvalidEntity(DTO dto) throws Exception {
        info("testReplaceWithInvalidEntity({})", dto);
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
        info("testFindAll()");
        mockMvc.perform(get(getMapping()))
                .andExpect(status().isOk());
    }

    @Test 
    @Order(3)
    void testFindByUidExistingEntity() throws Exception {
        info("testFindByUidExistingEntity()");
        Integer existingUid = provideUidOfExistingEntity();
        mockMvc.perform(get(getMapping() + "/" + existingUid))
                .andExpect(status().isOk());
    }

    @Test 
    @Order(3)
    void testFindByIdNonExistingEntity() throws Exception {
        info("testFindByIdNonExistingEntity()");
        Integer nonExistingUid = provideUidOfInvalidEntity();
        mockMvc.perform(get(getMapping() + "/" + nonExistingUid))
                .andExpect(status().isNotFound());
    }

    @Test 
    @Order(4)
    void testDeleteExistingEntity() throws Exception {
        info("testDeleteExistingEntity()");
        Integer unconstrainedUid = provideUidOfUnconstrainedEntity();
        mockMvc.perform(delete(getMapping() + "/" + unconstrainedUid))
                .andExpect(status().isNoContent());
    }

    @Test 
    @Order(4)
    void testDeleteNonExistingEntity() throws Exception {
        info("testDeleteNonExistingEntity()");
        Integer nonExistingUid = provideUidOfInvalidEntity();
        mockMvc.perform(delete(getMapping() + "/" + nonExistingUid))
                .andExpect(status().isNotFound());
    }
}
