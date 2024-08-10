package hihi.content.bom;

import com.fasterxml.jackson.annotation.JsonTypeName;
import hihi.content.common.dataModel.AbstractDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("Bom")
public class BomDto extends AbstractDto {

    private Integer projectId;
    private Integer materialId;
    private String materialName;
    private Float qty;

}
