package org.epm.invoice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.epm.common.model.IDTO;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@JsonTypeName("Invoice")
public class InvoiceDTO
        extends InvoiceData implements IDTO {

    private String action;

    @JsonIgnore
    public boolean isValidDTO() {
        return super.isValidDTO() || getAction() != null;
    }

}
