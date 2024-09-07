package hihi.adapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import hihi.application.config.GuiConfig;
import hihi.application.config.ModuleConfig;
import hihi.content.common.dataModel.AbstractContent;
import hihi.content.common.dataModel.AbstractDto;
import hihi.event.ErrorMessageBox;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@ToString
public final class WarehouseAdapter {
    private final RestTemplate restTemplate = new RestTemplate();
    private final ModuleConfig moduleConfig;
    private final Integer parentUid;

    public WarehouseAdapter(ModuleConfig moduleConfig) {
        this.moduleConfig = moduleConfig;
        this.parentUid = null;
    }

    public WarehouseAdapter(ModuleConfig moduleConfig, Integer parentUid) {
        this.moduleConfig = moduleConfig;
        this.parentUid = parentUid;
    }

    public <Content extends AbstractContent> Content create(final Content content) {
        return mapDtoToContent(restTemplate.postForObject(getEndpoint(), content, moduleConfig.getDtoClass()));
    }

    public <Content extends AbstractContent> List<Content> getAll() {
        String url = getEndpoint() + "?page=0&size=" + getCount();
        log.info("\033[34m Request: GET, url={}", url);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        if (response.getStatusCode() == HttpStatus.NO_CONTENT ||
                response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) return null;
        List<Content> result = parseJsonPageToContentList(response.getBody());
        return result;
    }

    public <Content extends AbstractContent> List<Content> getPage(final int page, final int size) {
        String url = getEndpoint() + "?page=" + page + "&size=" + (size > 0 ? size : 1);
        log.info("\033[34m Request: GET, url={}", url);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        if (response.getStatusCode() == HttpStatus.NO_CONTENT ||
                response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) return null;
        List<Content> result = parseJsonPageToContentList(response.getBody());
        log.info("Content: {}", result);
        return result;
    }

    public <Content extends AbstractContent> Content getByUid(final Integer uid) {
        String url = getEndpoint() + "/" + uid;
        log.info("\033[34m Request: GET, url={}\033[m", url);
        return mapDtoToContent(restTemplate.getForObject(url, moduleConfig.getDtoClass()));
    }

    public int getCount(){
        String url = getEndpoint() + "?page=0&size=1";
        log.info("\033[34m Request: GET, url={}", url);
        try {
            String response = restTemplate.getForObject(url, String.class);
            JsonNode json = new ObjectMapper().readTree(response);
            return Math.max(json.get("page").get("totalElements").asInt(), 1);
        } catch (RestClientException e) {
            ErrorMessageBox.showError("Connection to server failed. Check server state and connection and restart " +
                    "application.");
            return 0;
        } catch (JsonProcessingException e) {
            log.error("Failed to parse response: {}", e.getMessage());
            return 1;
        }
    }

    public <Content extends AbstractContent> Content update(final Content content) {
        String url = getEndpoint() + "/" + content.uidProperty().get();
        log.info("\033[34m Request: PATCH, url={}, content={}\033[0m", url, content);
        return mapDtoToContent(restTemplate.patchForObject(url, content, moduleConfig.getDtoClass()));
    }

    public boolean delete(final Integer uid) {
        String url = getEndpoint() + "/" + uid;
        log.info("\033[34m Request: DELETE, url={} \033[m", url);
        try {
            ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.DELETE, null, Void.class);
            return response.getStatusCode() == HttpStatus.NO_CONTENT;
        } catch (HttpClientErrorException.NotFound e) {
            log.info("{} with id {} not found", moduleConfig.getModuleName(), uid);
        } catch (RestClientException e) {
            log.error("Error while deleting {}: {}", moduleConfig.getModuleName(), e.getMessage());
        }
        return false;
    }

    private String getEndpoint() throws IllegalStateException {
        if (moduleConfig.isDependant() && parentUid != null) {
            return GuiConfig.API_URL + moduleConfig.getEndpoint().replace("{pid}", String.valueOf(parentUid));
        } else if (moduleConfig.isPrimary()) {
            return GuiConfig.API_URL + moduleConfig.getEndpoint();
        }
        throw new IllegalStateException("Adapter not configured");
    }

    @SuppressWarnings("unchecked")
    private <DTO extends AbstractDto, Content extends AbstractContent> Content mapDtoToContent(final DTO dto) {
        try {
            return (Content) moduleConfig.getContentConstructor().newInstance(dto);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | IllegalArgumentException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    private <DTO extends AbstractDto, Content extends AbstractContent> DTO mapContentToDto(final Content content) {
        try {
            return (DTO) moduleConfig.getDtoConstructor().newInstance(content);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | IllegalArgumentException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    private <DTO extends AbstractDto, Content extends AbstractContent> List<Content> mapDtoListToContentList(final List<DTO> dtoList) {
        List<Content> contentList = new ArrayList<>();
        for (DTO dto : dtoList) {
            try {
                contentList.add((Content) moduleConfig.getContentConstructor().newInstance(dto));
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | IllegalArgumentException e) {
                log.error(e.getMessage());
                return null;
            }
        }
        return contentList;
    }

    private <Content extends AbstractContent> List<Content> parseJsonPageToContentList(final String response) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<Content> result = mapDtoListToContentList(
                    mapper.convertValue(
                            mapper.readTree(response).get("content"),
                            mapper.getTypeFactory().constructCollectionType(
                                    List.class, moduleConfig.getDtoClass()
                            )
                    )
            );
            return result;
        } catch (JsonProcessingException e) {
            log.error("Failed to parse json response: {}", e.getMessage());
            return new ArrayList<>();
        }
    }

}
