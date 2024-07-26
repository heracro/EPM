package hihi.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("Delivery")
public class DeliveryDto {

    private Integer materialId;
    private LocalDate orderedDate;
    private LocalDate plannedDate;
    private LocalDate deliveredDate;
    private Integer qty;
    private Float price;
    private Float totalPrice;
    private String status;
    private String store;
}
