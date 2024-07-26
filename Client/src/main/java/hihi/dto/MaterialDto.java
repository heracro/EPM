package hihi.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("Material")
public class MaterialDto {

    private String name;
    private String norm;
    private String dimension;
    private Float weight;
    private Integer totalQty;
    private Integer freeQty;
    private String store;
    private String datasheet;

}
