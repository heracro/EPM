package hihi.content.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TagType {
    PROJECT     ("Projekt"),
    CHANGELOG   ("Changelog"),
    BOTH        ("Dowolny");

    private final String type;

    @JsonValue
    public String toValue() {
        return name().toLowerCase();
    }

    @JsonCreator
    public static TagType forValue(String value) throws IllegalArgumentException {
        for (TagType status : values()) {
            if (status.name().equalsIgnoreCase(value) || status.getType().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown DeliveryStatus: " + value);
    }
}
