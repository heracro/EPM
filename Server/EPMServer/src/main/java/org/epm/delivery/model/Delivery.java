package org.epm.delivery.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.epm.common.annotations.Stringify;
import org.epm.material.model.Material;

import java.time.LocalDate;

@Data
@Entity
@Stringify
@NoArgsConstructor
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "material_id")
    private Material material;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "0.00")
    private Float unitPrice;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "0.00")
    private Float totalPrice;

    private Integer qty;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private String store;

    public void setQty(Integer qty) {
        this.qty = qty;
        if (this.unitPrice == null) {
            this.unitPrice = 0f;
        }
        this.totalPrice = this.unitPrice * this.qty;
    }

    public void setUnitPrice(Float price) {
        if (price == null) price = 0f;
        this.unitPrice = Math.round(100 * price) / 100f;
        this.totalPrice = this.unitPrice * this.qty;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = Math.round(100 * totalPrice) / 100f;
        this.unitPrice = Math.round(100 * totalPrice / this.qty) / 100f;
    }
}
