package hihi.adapters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import hihi.content.common.dataModel.AbstractDto;

import java.util.List;

public abstract class MainResourceAbstractAdapter<DTO extends AbstractDto> extends AbstractWarehouseAdapter<DTO> {

    protected abstract String getEndpoint();

    public List<DTO> getAll() {
        String response = restTemplate.getForObject(getEndpoint() + "?page=0&size=" + getCount(), String.class);
        return parseJsonToList(response);
    }

    public int getCount() {
        String response = restTemplate.getForObject(getEndpoint() + "?page=0&size=1", String.class);
        try {
            JsonNode json = new ObjectMapper().readTree(response);
            return json.get("page").get("totalElements").asInt();
        } catch (JsonProcessingException e) {
            return 0;
        }
    }

    public DTO getByUid(Integer uid) {
        return restTemplate.getForObject(getEndpoint() + "/" + uid, getDtoClass());
    }

    public DTO create(DTO dto) {
        return restTemplate.postForObject(getEndpoint(), dto, getDtoClass());
    }

    public DTO update(DTO dto) {
        return restTemplate.patchForObject(getEndpoint() + "/" + dto.getUid(), dto, getDtoClass());
    }

    public boolean delete(Integer uid) {
        return delete(getEndpoint(), uid);
    }
}
