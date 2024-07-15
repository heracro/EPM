package org.epm.invoice.controller;

import lombok.RequiredArgsConstructor;
import org.epm.common.controller.AbstractRestController;
import org.epm.invoice.model.InvoiceDTO;
import org.epm.invoice.service.InvoiceService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/invoices")
@RequiredArgsConstructor
public class InvoiceController extends AbstractRestController<InvoiceDTO> {

    private final InvoiceService invoiceService;

    @Override
    public InvoiceService getEntityService() {
        return invoiceService;
    }

}
