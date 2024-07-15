package org.epm.invoice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.epm.common.model.EntityListener;
import org.epm.common.model.IEntity;
import org.epm.delivery.model.DeliveryEntity;
import org.epm.shop.model.ShopEntity;

import java.util.List;

@Slf4j
@Setter
@Getter
@Entity
@Table(name = "invoices")
@NoArgsConstructor
@EntityListeners(EntityListener.class)
public class InvoiceEntity extends InvoiceData implements IEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_uid", referencedColumnName = "uid", nullable = false)
    private ShopEntity shop;

    @JsonIgnore
    @OneToMany(mappedBy = "invoice", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<DeliveryEntity> deliveries;

}
