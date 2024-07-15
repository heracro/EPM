package org.epm.delivery.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.epm.common.model.EntityListener;
import org.epm.common.model.IEntity;
import org.epm.common.utils.ConsoleStringUtils;
import org.epm.invoice.model.InvoiceData;
import org.epm.invoice.model.InvoiceEntity;
import org.epm.material.model.MaterialData;
import org.epm.material.model.MaterialEntity;

import java.awt.*;

@Slf4j
@Getter
@Setter
@Entity
@Table(name = "deliveries")
@TableGenerator(
        name = "delivery_gen",
        table = "id_gen",
        pkColumnName = "gen_name",
        valueColumnName = "gen_val",
        pkColumnValue = "delivery_id",
        allocationSize = 1)
@NoArgsConstructor
@EntityListeners(EntityListener.class)
public class DeliveryEntity extends DeliveryData implements IEntity {

    @ManyToOne
    @JoinColumn(name = "material_id", referencedColumnName = "uid", nullable = false)
    private MaterialEntity material;

    @ManyToOne
    @JoinColumn(name = "invoice_id", referencedColumnName = "uid")
    private InvoiceEntity invoice;

    @PrePersist
    @PreUpdate
    public void setDefaults() {
        if (getUnitPrice() == null) setUnitPrice(0f);
        if (getTotalPrice() == null) setTotalPrice(0f);
    }

    @Override
    public void setMaterial(MaterialData material) {
        log.warn(ConsoleStringUtils.fontColor(Color.RED,
                "DeliveryDTO::setMaterial(MaterialData material)"));
        this.material = (MaterialEntity) material;
    }

    @Override
    public void setInvoice(InvoiceData invoice) {
        log.warn(ConsoleStringUtils.fontColor(Color.RED,
                "DeliveryDTO::setInvoice(InvoiceData invoice)"));
        this.invoice = (InvoiceEntity) invoice;
    }

}
