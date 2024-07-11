package org.epm.invoice.controller;

import org.epm.common.controller.AbstractEntityController;
import org.epm.invoice.model.InvoiceDTO;
import org.epm.invoice.service.InvoiceService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController extends AbstractEntityController<InvoiceDTO> {

    public InvoiceController(InvoiceService invoiceService) {
        super(invoiceService);
    }

    @Override
    public String getMapping() {
        return "/api/invoices";
    }
}
