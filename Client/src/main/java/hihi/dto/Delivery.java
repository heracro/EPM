package hihi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Delivery {
    private int deliveryId;
    private Integer materialId;
    private String materialName;
    private LocalDate orderedDate;
    private LocalDate plannedDate;
    private LocalDate deliveredDate;
    private Integer qty;
    private Float price;
    private Float totalPrice;
    private String status;
    private String store;
}
