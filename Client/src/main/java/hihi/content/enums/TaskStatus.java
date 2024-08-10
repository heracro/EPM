package hihi.content.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TaskStatus {
    PLANNED     ("Zaplanowane"),
    ONGOING     ("Trwające"),
    COMPLETED   ("Ukończone"),
    CANCELLED   ("Anulowane");

    private final String status;

    @JsonValue
    public String toValue() {
        return name().toLowerCase();
    }

    @JsonCreator
    public static TaskStatus forValue(String value) throws IllegalArgumentException {
        for (TaskStatus status : values()) {
            if (status.name().equalsIgnoreCase(value) || status.getStatus().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown DeliveryStatus: " + value);
    }
}
