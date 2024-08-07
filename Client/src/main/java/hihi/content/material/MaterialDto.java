package hihi.content.material;

import hihi.content.common.AbstractDto;
import hihi.content.enums.Unit;
import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
//@JsonTypeName("Material")
public class MaterialDto extends AbstractDto {

    private String name;
    private String norm;
    private String datasheet;
    private String dimensions;
    private String description;
    private Float weight;
    private Integer totalQty;
    private Integer freeQty;
    private String store;
    private Unit unit;

}
