package org.epm.invoice.model;

import jakarta.persistence.*;
import lombok.*;
import org.epm.common.model.IEntity;

@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@Entity
@Table(name = "invoices")
@NoArgsConstructor
public class InvoiceEntity
        extends InvoiceData implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long privateId;

}
