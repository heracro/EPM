package hihi.dto.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BomStatus {
    MISSING     ("Brak"),
    PARTIAL     ("Częściowo zarezerwowany"),
    COMPLETE    ("Skompletowany"),
    TAKEN       ("Wydany");

    private final String status;

    @JsonValue
    public String toValue() {
        return name().toLowerCase();
    }

    @JsonCreator
    public static BomStatus forValue(String value) throws IllegalArgumentException {
        for (BomStatus status : values()) {
            if (status.name().equalsIgnoreCase(value) || status.getStatus().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown BomStatus: " + value);
    }

}
