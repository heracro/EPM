package hihi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bom {
    private Integer id;
    private Integer projectId;
    private Integer materialId;
    private String materialName;
    private Integer qty;
}
