package org.epm.delivery.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.epm.common.configuration.Config;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.epm.delivery.enums.DeliveryStatus;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@RequiredArgsConstructor
public class DeliverySearchCriteria {

    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate orderDateFrom;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate orderDateTo;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate deliveryDateFrom;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate deliveryDateTo;
    private String store;
    private List<DeliveryStatus> statuses;

    public void setStatus(DeliveryStatus status) {
        this.statuses = List.of(status);
    }

    @Override
    public String toString() {
        String result = "";
        result += ("{partialName = " + (name == null ? "NULL" : name));
        result += ("; orderedDateFrom = " + (orderDateFrom == null ? "NULL" : Config.localDateToString(orderDateFrom)));
        result += ("; orderedDateTo = " + (orderDateTo == null ? "NULL" : Config.localDateToString(orderDateTo)));
        result += ("; plannedDateFrom = " + (deliveryDateFrom == null ? "NULL" : Config.localDateToString(deliveryDateFrom)));
        result += ("; plannedDateTo = " + (deliveryDateTo == null ? "NULL" : Config.localDateToString(deliveryDateTo)));
        result += ("; partialStore = " + (store == null ? "NULL" : store));
        result += ("; status = " + (statuses == null ? "NULL" : statuses.size() == 1 ? statuses.getFirst() : "(" + statuses.size() + ")}"));
        return result;
    }
}
