package org.epm.invoice.service;

import lombok.RequiredArgsConstructor;
import org.epm.common.service.AbstractService;
import org.epm.invoice.model.InvoiceDTO;
import org.epm.invoice.model.InvoiceEntity;
import org.epm.invoice.model.InvoiceMapper;
import org.epm.invoice.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InvoiceService extends AbstractService<InvoiceEntity, InvoiceDTO> {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceMapper invoiceMapper;

    @Override
    public InvoiceMapper getMapper() {
        return invoiceMapper;
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
