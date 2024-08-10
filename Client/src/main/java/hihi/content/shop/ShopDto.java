package hihi.content.shop;

import com.fasterxml.jackson.annotation.JsonTypeName;
import hihi.content.common.dataModel.AbstractDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonTypeName("Shop")
public class ShopDto extends AbstractDto {

    private String name;
    private String webpage;
    private String address;
    private String email;
    private String phone;
    private String memo;

}
