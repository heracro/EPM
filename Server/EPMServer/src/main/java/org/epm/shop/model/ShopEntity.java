package org.epm.shop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.epm.common.model.IEntity;
import org.epm.invoice.model.InvoiceEntity;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "shops")
@TableGenerator(
        name = "shop_gen",
        table = "id_gen",
        pkColumnName = "gen_name",
        valueColumnName = "gen_val",
        pkColumnValue = "shop_id",
        allocationSize = 1)
@NoArgsConstructor
public class ShopEntity extends ShopData implements IEntity {

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<InvoiceEntity> invoices = new HashSet<>();

}
