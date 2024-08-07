package hihi.adapters;

import hihi.application.config.GuiConfig;
import hihi.content.delivery.DeliveryDto;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@NoArgsConstructor
public class DeliveryAdapter extends MainResourceAbstractAdapter<DeliveryDto> {

    @Override
    public String getEndpoint() {
        return GuiConfig.API_URL + "/deliveries";
    }

    @Override
    public Class<DeliveryDto> getDtoClass() {
        return DeliveryDto.class;
    }

    @Override
    public String getEntityName() {
        return "Delivery";
    }

}
