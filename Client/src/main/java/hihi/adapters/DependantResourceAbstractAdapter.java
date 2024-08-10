package hihi.adapters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import hihi.content.common.dataModel.AbstractDto;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public abstract class DependantResourceAbstractAdapter<DTO extends AbstractDto> extends AbstractWarehouseAdapter<DTO> {

    protected abstract String getEndpoint(Integer parentUid);

    public List<DTO> getAll(Integer parentUid) {
        String response = restTemplate
                .getForObject(getEndpoint(parentUid) + "?page=0&size=" + getCount(parentUid), String.class);
        return parseJsonToList(response);
    }

    public int getCount(Integer parentUid) {
        String url = getEndpoint(parentUid) + "?page=0&size=1";
        String response = restTemplate.getForObject(url, String.class);
        try {
            JsonNode json = new ObjectMapper().readTree(response);
            return json.get("page").get("totalElements").asInt();
        } catch (JsonProcessingException e) {
            return 0;
        }
    }

    public DTO getByUid(Integer parentUid, Integer uid) {
        return restTemplate.getForObject(getEndpoint(parentUid) + "/" + uid, getDtoClass());
    }

    public DTO create(Integer parentUid, DTO dto) {
        return restTemplate.postForObject(getEndpoint(parentUid), dto, getDtoClass());
    }

    public DTO update(Integer parentUid, DTO dto) {
        return restTemplate.patchForObject(getEndpoint(parentUid) + "/" + dto.getUid(), dto, getDtoClass());
    }

    public boolean delete(Integer parentUid, Integer uid) {
        return delete(getEndpoint(parentUid), uid);
    }
}
