package hihi.adapters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import hihi.application.config.GuiConfig;
import hihi.dto.MaterialDto;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@NoArgsConstructor
public class MaterialAdapter {

    private final RestTemplate restTemplate = new RestTemplate();
    private final static String MATERIALS_URL = GuiConfig.API_URL + "/materials";

    public List<MaterialDto> getMaterials() throws IOException {
        String url = MATERIALS_URL + "?page=0&size=" + getMaterialsCount();
        String response = restTemplate.getForObject(url, String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(response).get("content");
        return mapper.convertValue(node, new TypeReference<List<MaterialDto>>() {});
    }

    public int getMaterialsCount() throws IOException {
        String response = restTemplate.getForObject(MATERIALS_URL + "?page=0&size=1", String.class);
        JsonNode root = new ObjectMapper().readTree(response);
        return root.get("page").get("totalElements").asInt();
    }

    public MaterialDto getMaterialByUid(Integer uid) {
        return restTemplate.getForObject(MATERIALS_URL + "/" + uid, MaterialDto.class);
    }

    public MaterialDto createMaterial(MaterialDto material) {
        return restTemplate.postForObject(MATERIALS_URL, material, MaterialDto.class);
    }

    public MaterialDto updateMaterial(MaterialDto material) {
        return restTemplate.patchForObject(MATERIALS_URL + "/" + material.getUid(), material, MaterialDto.class);
    }

    public boolean deleteMaterial(Integer uid) {
        String url = MATERIALS_URL + "/" + uid;
        try {
            ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.DELETE, null, Void.class);
            return response.getStatusCode() == HttpStatus.NO_CONTENT;
        } catch (HttpClientErrorException.NotFound e) {
            log.info("Material with id {} not found", uid);
        } catch (RestClientException e) {
            log.error("Error while deleting Material", e);
        }
        return false;
    }

}
