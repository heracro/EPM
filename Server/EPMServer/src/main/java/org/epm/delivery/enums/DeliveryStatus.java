package org.epm.delivery.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DeliveryStatus {
    SHOPPING_LIST   ("Lista zakupów"),
    ORDERED         ("Zamówione"),
    DELIVERED       ("Dostarczone"),
    COMPLAINT       ("Reklamacja"),
    CANCELLED       ("Anulowane");

    private final String status;

    @JsonValue
    public String toValue() {
        return name().toLowerCase();
    }

    @JsonCreator
    public static DeliveryStatus forValue(String value) throws IllegalArgumentException {
        for (DeliveryStatus status : values()) {
            if (status.name().equalsIgnoreCase(value) || status.getStatus().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown DeliveryStatus: " + value);
    }

}
