package org.epm.invoice.repository;

import org.epm.common.repository.IRepository;
import org.epm.invoice.model.InvoiceEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends IRepository<InvoiceEntity> {
}
