package hihi.content.invoice;

import hihi.content.common.contentDetails.ContentDetailsLayoutController;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@ToString(callSuper = true)
@Component
public class InvoiceDetailsLayoutController
        extends ContentDetailsLayoutController<Invoice> {

    public InvoiceDetailsLayoutController() {
        super("Invoice");
    }

    public void initialize() {
        log.info("\033[92m super.initialize() \033[0m");
        super.initialize();
        log.info("\033[92m initialize() \033[0m");
    }

    @Override
    public void setContent(Invoice invoice) {
        log.warn("\033[92m UNSUPPORTED! setContent({}) \033[0m", invoice);

    }
}
