package hihi.adapters;

import hihi.application.config.GuiConfig;
import hihi.content.material.MaterialDto;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@NoArgsConstructor
public class MaterialAdapter extends MainResourceAbstractAdapter<MaterialDto> {

    @Override
    protected String getEndpoint() {
        return GuiConfig.API_URL + "/materials";
    }

    @Override
    protected Class<MaterialDto> getDtoClass() {
        return MaterialDto.class;
    }

    @Override
    protected String getEntityName() {
        return "Material";
    }

}
