package hihi.content.delivery;

import hihi.adapters.DeliveryAdapter;
import hihi.content.common.contentDetails.ContentDetailsLayoutController;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@ToString(callSuper = true)
@Component
public class DeliveryDetailsLayoutController
        extends ContentDetailsLayoutController<Delivery, DeliveryDto, DeliveryAdapter> {

    public DeliveryDetailsLayoutController() {
        super(new DeliveryAdapter(), "Delivery");
        log.info("\033[92m DeliveryDetailsLayoutController() \033[m");
    }

    @Override
    public void setContent(Delivery delivery) {
        log.info("\033[92m setContent() \033[m");
    }

}
