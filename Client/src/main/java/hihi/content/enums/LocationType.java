package hihi.content.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LocationType implements DeserializableEnum<LocationType> {
    LOCAL           ("Lokalny"),
    REMOTE_HTTP     ("Zdalny http"),
    REMOTE_HTTPS    ("Zdalny https"),
    REMOTE_FTP      ("Zdalny ftp"),
    SHELF           ("Papierowy");

    private final String value;

    @JsonValue
    public String toValue() {
        return name().toLowerCase();
    }

    @JsonCreator
    public static LocationType forValue(String value) throws IllegalArgumentException {
        for (LocationType status : values()) {
            if (status.name().equalsIgnoreCase(value) || status.getValue().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown DeliveryStatus: " + value);
    }
}
