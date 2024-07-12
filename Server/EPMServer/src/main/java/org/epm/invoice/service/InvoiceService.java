package org.epm.invoice.service;

import org.epm.common.service.AbstractService;
import org.epm.invoice.model.InvoiceDTO;
import org.epm.invoice.model.InvoiceEntity;
import org.epm.invoice.model.InvoiceMapper;
import org.epm.invoice.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService extends AbstractService<InvoiceEntity, InvoiceDTO> {

    private final InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository, InvoiceMapper mapper) {
        super(mapper);
        this.invoiceRepository = invoiceRepository;
    }


    @Override
    protected InvoiceRepository getRepository() {
        return invoiceRepository;
    }

    @Override
    public String getEntityName() {
        return "Invoice";
    }
}
