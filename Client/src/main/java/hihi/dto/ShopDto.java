package hihi.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonTypeName("Shop")
public class ShopDto {

    private String name;
    private String webpage;
    private String address;
    private String email;
    private String phone;
    private String memo;

}
