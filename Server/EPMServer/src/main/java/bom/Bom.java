package bom;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import material.Material;
import project.Project;

@Data
@Entity
@NoArgsConstructor
public class Bom {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "material_id")
    private Material material;

    @Enumerated(EnumType.STRING)
    private BomStatus status;

    private Integer qty;
    private Integer reservedQty;

    public String toString() {
        return "Bom{(" + id + "), "
                + project.getName() + "(" + project.getId() + "), "
                + material.getName() + "(" + material.getId() + "), total "
                + qty + ", res. " + reservedQty + "}";
    }
}
