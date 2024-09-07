package hihi.content.delivery;

import hihi.content.common.contentDetails.ContentDetailsLayoutController;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ToString(callSuper = true)
public class DeliveryDetailsLayoutController
        extends ContentDetailsLayoutController<Delivery> {

    public DeliveryDetailsLayoutController(Delivery delivery) {
        super("Delivery", delivery);
        log.info("\033[92m DeliveryDetailsLayoutController() \033[m");
    }

    public void setContent() {
        log.info("\033[92m setContent() \033[m");
    }

}
