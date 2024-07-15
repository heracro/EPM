package org.epm.delivery.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public class DeliveryStatistics {
    int totalCount;
    int shoppingListCount;
    int orderedCount;
    int deliveredInNext3DaysCount;
    int deliveredInNext7DaysCount;
    int deliveredCount;
    LocalDate farthestPlannedDelivery;
    int shortestDeliveryTime;
    int avgDeliveryTime;
    int longestDeliveryTime;
}
