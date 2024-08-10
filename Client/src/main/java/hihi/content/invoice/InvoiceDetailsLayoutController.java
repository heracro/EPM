package hihi.content.invoice;

import hihi.adapters.InvoiceAdapter;
import hihi.content.common.contentDetails.ContentDetailsLayoutController;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@ToString(callSuper = true)
@Component
public class InvoiceDetailsLayoutController
        extends ContentDetailsLayoutController<Invoice, InvoiceDto, InvoiceAdapter> {

    public InvoiceDetailsLayoutController() {
        super(new InvoiceAdapter(), "Invoice");
        log.warn("\033[92m UNSUPPORTED! InvoiceDetailsLayoutController() \033[m");
    }

    public void initialize() {
        log.warn("\033[92m UNSUPPORTED! initialize() \033[0m");
    }

    @Override
    public void setContent(Invoice invoice) {
        log.warn("\033[92m UNSUPPORTED! setContent({}) \033[0m", invoice);

    }
}
