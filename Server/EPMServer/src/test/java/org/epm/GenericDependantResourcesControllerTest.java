package org.epm;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletContext;
import org.epm.common.model.AbstractModuleData;
import org.epm.common.model.IDTO;
import org.epm.common.model.IDependantEntity;
import org.epm.common.model.IEntity;
import org.epm.material.controller.MaterialController;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
public abstract class GenericDependantResourcesControllerTest<
        Entity extends IDependantEntity<ParentEntity>,
        ParentEntity extends IEntity,
        DTO extends AbstractModuleData & IDTO> {

    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    protected WebApplicationContext webApplicationContext;
    @Autowired
    private DatabaseSeeder databaseSeeder;

    protected abstract AbstractDependantTestParameterProvider<Entity, ParentEntity, DTO> getTestParameterProvider();
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

    private Integer getValidParentUid() {
        return getTestParameterProvider().getValidParentUid();
    }

    private String getReplacedMapping(Integer uid) {
        StringBuilder builder = new StringBuilder(getMapping());
        return builder.replace(builder.indexOf("{"), builder.indexOf("}") - 1, uid.toString()).toString();
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
        assertNotNull(null, "Test not implemented yet");
    }

    @ParameterizedTest
    @MethodSource("provideFewDTOsWhichAreInvalidEntity")
    @Order(1)
    void testCreateInvalidEntityAndFail(DTO dto) throws Exception {
        assertNotNull(null, "Test not implemented yet");
    }

    @ParameterizedTest
    @MethodSource("provideDTOsWithSingleValidAttribute")
    @Order(2)
    void testUpdateEntityWithSingleValidParameter(DTO dto) {
        assertNotNull(null, "Test not implemented yet");
    }

    @ParameterizedTest
    @MethodSource("provideDTOsWithSingleInvalidAttribute")
    @Order(2)
    void testUpdateEntityWithSingleInvalidParameter(DTO dto) {
        assertNotNull(null, "Test not implemented yet");
    }

    @ParameterizedTest
    @MethodSource("provideFewDTOsWhichAreValidEntity")
    @Order(2)
    void testReplaceWithValidEntity(DTO dto) throws Exception {
        assertNotNull(null, "Test not implemented yet");
    }

    @ParameterizedTest
    @MethodSource("provideFewDTOsWhichAreInvalidEntity")
    @Order(2)
    void testReplaceWithInvalidEntity(DTO dto) throws Exception {
        assertNotNull(null, "Test not implemented yet");
    }

    @Test
    @Order(3)
    void testFindAll() throws Exception {
        assertNotNull(null, "Test not implemented yet");
    }

    @Test
    @Order(3)
    void testFindByUidExistingEntity() throws Exception {
        assertNotNull(null, "Test not implemented yet");
    }

    @Test
    @Order(3)
    void testFindByIdNonExistingEntity() throws Exception {
        assertNotNull(null, "Test not implemented yet");
    }

    @Test
    @Order(4)
    void testDeleteExistingEntity() throws Exception {
        assertNotNull(null, "Test not implemented yet");
    }

    @Test
    @Order(4)
    void testDeleteNonExistingEntity() throws Exception {
        assertNotNull(null, "Test not implemented yet");
    }

}
