package hihi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Material {
    private Integer materialId;
    private String name;
    private String norm;
    private String dimension;
    private Float weight;
    private Integer totalQty;
    private Integer freeQty;
    private String store;
    private String datasheet;
}
