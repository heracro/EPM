package hihi.content.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProjectCause implements DeserializableEnum<ProjectCause> {
    HOBBY                   ("Hobby"),
    HOME_IMPROVEMENT        ("Usprawnienia domu"),
    ORDERED_COMMERCIAL      ("Zamówienie komercyjne"),
    ORDERED_NON_COMMERCIAL  ("Zamówienie po znajomości"),
    EDUCATIONAL             ("Projekt edukacyjny lub szkolny"),
    GIFT                    ("Prezent");

    private final String value;

    @JsonValue
    public String toValue() {
        return name().toLowerCase();
    }

    @JsonCreator
    public static ProjectCause forValue(String value) throws IllegalArgumentException {
        for (ProjectCause status : values()) {
            if (status.name().equalsIgnoreCase(value) || status.getValue().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown DeliveryStatus: " + value);
    }

}
