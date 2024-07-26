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
@JsonTypeName("Bom")
public class BomDto {

    private Integer projectId;
    private Integer materialId;
    private String materialName;
    private Integer qty;

}
