package material;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;
    private String norm;
    private String datasheet;
    private String dimensions;
    private Float weight;
    private Integer totalQty;
    private Integer freeQty;

    @Enumerated(EnumType.STRING)
    private Unit unit;

    @Override
    public String toString() {
        return "Material {" + name + "(" + id + "), "
                + norm + ", qty: " + freeQty + "/" + totalQty + " " + unit + "}";
    }
}
