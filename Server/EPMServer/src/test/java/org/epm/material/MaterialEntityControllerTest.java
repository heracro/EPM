package org.epm.material;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletContext;
import org.epm.EPM;
import org.epm.material.model.MaterialEntity;
import org.epm.material.model.Unit;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = EPM.class)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MaterialEntityControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MaterialController materialController;
    @Autowired
    private MaterialService materialService;
    @Autowired
    private MaterialRepository materialRepository;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach void setup() throws Exception {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(this.webApplicationContext)
                .build();
    }

    @Test
    @Order(1)
    public void testContextIsInitialized() {
        ServletContext servletContext = webApplicationContext.getServletContext();
        assertNotNull(servletContext, "Servlet context is null");
        assertInstanceOf(MockServletContext.class, servletContext, "Servlet context is not instance of MockServletContext");
        assertNotNull(webApplicationContext.getBean(MaterialController.class), "Retrieving bean 'MaterialController' from WebAppContext returned nothing");
    }

    @Test
    @Order(2)
    void testCreateValidMaterial() throws Exception {
        MaterialEntity materialEntity = new MaterialEntity();
        materialEntity.setName("Steel Beam");
        materialEntity.setDimensions("200x200x1000");
        materialEntity.setWeight(250.0f);
        materialEntity.setTotalQty(50);
        materialEntity.setFreeQty(50);
        materialEntity.setUnit(Unit.G);
        String materialJson = objectMapper.writeValueAsString(materialEntity);
        mockMvc.perform(post("/materials")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(materialJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("Steel Beam"))
                .andExpect(jsonPath("$.dimensions").value("200x200x1000"))
                .andExpect(jsonPath("$.weight").value(250))
                .andExpect(jsonPath("$.totalQty").value(50))
                .andExpect(jsonPath("$.freeQty").value(50))
                .andExpect(jsonPath("$.unit").value("G"));

    }




}
