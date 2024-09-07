package hihi.content.invoice;

import hihi.content.common.contentDetails.ContentDetailsLayoutController;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ToString(callSuper = true)
public class InvoiceDetailsLayoutController
        extends ContentDetailsLayoutController<Invoice> {

    public InvoiceDetailsLayoutController(Invoice invoice) {
        super("Invoice", invoice);
    }

    public void initialize() {
        log.info("\033[92m super.initialize() \033[0m");
        super.initialize();
        log.info("\033[92m initialize() \033[0m");
    }

    public void setContent() {
        log.warn("\033[92m UNSUPPORTED! setContent() \033[0m");

    }
}
