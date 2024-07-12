package org.epm.shop.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.epm.common.model.EntityListener;
import org.epm.common.model.IEntity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "shops")
@NoArgsConstructor
@EntityListeners(EntityListener.class)
public class ShopEntity extends ShopData implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long privateId;
}
