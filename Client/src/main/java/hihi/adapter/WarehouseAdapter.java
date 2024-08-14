package hihi.adapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import hihi.application.config.GuiConfig;
import hihi.application.config.ModuleConfig;
import hihi.content.common.dataModel.AbstractContent;
import hihi.content.common.dataModel.AbstractDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
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

    public List<? extends AbstractContent> getAll() {
        String url = getEndpoint() + "?page=0&size=" + getCount();
        log.info("\033[34m Request: GET, url={}", url);
        String response = restTemplate.getForObject(url, String.class);
        return parseJsonPageToContentList(response);
    }

    public List<? extends AbstractContent> getPage(final int page, final int size) {
        String url = getEndpoint() + "?page=" + page + "&size=" + size;
        log.info("\033[34m Request: GET, url={}", url);
        String response = restTemplate.getForObject(url, String.class);
        return parseJsonPageToContentList(response);
    }

    public <Content extends AbstractContent> Content getByUid(final Integer uid) {
        String url = getEndpoint() + "/" + uid;
        log.info("\033[34m Request: GET, url={}", url);
        return mapDtoToContent(restTemplate.getForObject(url, moduleConfig.getDtoClass()));
    }

    public int getCount() {
        String url = getEndpoint() + "?page=0&size=1";
        log.info("\033[34m Request: GET, url={}", url);
        String response = restTemplate.getForObject(url, String.class);
        try {
            JsonNode json = new ObjectMapper().readTree(response);
            return json.get("page").get("totalElements").asInt();
        } catch (JsonProcessingException e) {
            return 0;
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

    private List<? extends AbstractContent> parseJsonPageToContentList(final String response) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode content = mapper.readTree(response).get("content");
            return mapDtoListToContentList(mapper.convertValue(content,
                    mapper.getTypeFactory().constructCollectionType(List.class, moduleConfig.getDtoClass())));
        } catch (JsonProcessingException e) {
            log.error("Failed to parse json response: {}", e.getMessage());
            return new ArrayList<>();
        }
    }

}
