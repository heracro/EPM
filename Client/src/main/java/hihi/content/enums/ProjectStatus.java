package hihi.content.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProjectStatus implements DeserializableEnum<ProjectStatus> {
    PLANNED             ("Zaplanowany"),
    AWAITING_MATERIALS  ("Oczekuje na materiały"),
    READY               ("Gotowy do rozpoczęcia"),
    ONGOING             ("Trwa"),
    COMPLETED           ("Zakończony"),
    CANCELLED           ("Anulowany");

    private final String value;

    @JsonValue
    public String toValue() {
        return name().toLowerCase();
    }

    @JsonCreator
    public static ProjectStatus forValue(String value) throws IllegalArgumentException {
        for (ProjectStatus status : values()) {
            if (status.name().equalsIgnoreCase(value) || status.getValue().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown DeliveryStatus: " + value);
    }
}
