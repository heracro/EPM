package hihi.adapters;

import hihi.content.invoice.InvoiceDto;

public class InvoiceAdapter extends MainResourceAbstractAdapter<InvoiceDto> {
    @Override
    protected String getEndpoint() {
        return "/invoices";
    }

    @Override
    protected Class<InvoiceDto> getDtoClass() {
        return InvoiceDto.class;
    }

    @Override
    protected String getEntityName() {
        return "Invoice";
    }
}
