package org.epm.invoice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.epm.common.model.IDTO;
import org.epm.common.utils.ConsoleStringUtils;
import org.epm.shop.model.ShopDTO;
import org.epm.shop.model.ShopData;

import java.awt.*;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
@JsonTypeName("Invoice")
public class InvoiceDTO extends InvoiceData implements IDTO {

    private ShopDTO shop;
    private String action;

    @JsonIgnore
    public boolean isValidDTO() {
        return super.isValidDTO() || getAction() != null;
    }

    @Override
    public String toString() {
        return super.toString().substring(0, super.toString().length()-2)
                + ", action: " + getAction() + "}";
    }

}
