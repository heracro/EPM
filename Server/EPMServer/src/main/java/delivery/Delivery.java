package delivery;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import material.Material;

@Data
@Entity
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



}
