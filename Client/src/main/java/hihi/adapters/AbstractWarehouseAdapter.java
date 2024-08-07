package hihi.adapters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import hihi.content.common.AbstractDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public abstract class AbstractWarehouseAdapter<DTO extends AbstractDto> {

    protected final RestTemplate restTemplate = new RestTemplate();

    protected abstract Class<DTO> getDtoClass();

    protected abstract String getEntityName();

    protected List<DTO> parseJsonToList(String response) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode content = mapper.readTree(response).get("content");
            return mapper.convertValue(content,
                    mapper.getTypeFactory().constructCollectionType(List.class, getDtoClass()));
        } catch (JsonProcessingException e) {
            return new ArrayList<>();
        }
    }

    protected boolean delete(String endpoint, Integer uid) {
        String url = endpoint + "/" + uid;
        try {
            ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.DELETE, null, Void.class);
            return response.getStatusCode() == HttpStatus.NO_CONTENT;
        } catch (HttpClientErrorException.NotFound e) {
            log.info("{} with id {} not found", getEntityName(), uid);
        } catch (RestClientException e) {
            log.error("Error while deleting {}: {}", getEntityName(), e.getMessage());
        }
        return false;
    }
}
