package hihi.adapters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import hihi.application.config.GuiConfig;
import hihi.dto.ProjectDto;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
public class ProjectAdapter {

    private final RestTemplate restTemplate = new RestTemplate();
    private final static String PROJECTS_URL = GuiConfig.API_URL + "/projects";

    public List<ProjectDto> getProjects() throws IOException {
        String url = PROJECTS_URL + "?page=0&size=" + getProjectsCount();
        String response = restTemplate.getForObject(url, String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode content = mapper.readTree(response).get("content");
        return mapper.convertValue(content, new TypeReference<List<ProjectDto>>() {});
    }

    public int getProjectsCount() throws IOException {
        String url = PROJECTS_URL + "?page=0&size=1";
        String response = restTemplate.getForObject(PROJECTS_URL + "?page=0&size=1", String.class);
        JsonNode root = new ObjectMapper().readTree(response);
        return root.get("page").get("totalElements").asInt();
    }

    public ProjectDto getProjectByUid(Integer uid) {
        return restTemplate.getForObject(PROJECTS_URL + "/" + uid, ProjectDto.class);
    }

    public ProjectDto createProject(ProjectDto project) {
        return restTemplate.postForObject(PROJECTS_URL, project, ProjectDto.class);
    }

    public ProjectDto updateProject(ProjectDto project) {
        return restTemplate.patchForObject(PROJECTS_URL + "/" + project.getUid(), project, ProjectDto.class);
    }

    public boolean deleteProject(Integer uid) {
        String url = GuiConfig.API_URL + "/projects/" + uid;
        try {
            ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.DELETE, null, Void.class);
            return response.getStatusCode() == HttpStatus.NO_CONTENT;
        } catch (HttpClientErrorException.NotFound e) {
            log.info("Project with id {} not found", uid);
        } catch (RestClientException e) {
            log.error("Error while deleting project", e);
        }
        return false;
    }

}
