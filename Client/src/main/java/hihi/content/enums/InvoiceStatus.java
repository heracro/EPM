package hihi.content.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum InvoiceStatus {
    UNISSUED    ("Niewystawiona"),
    ISSUED      ("Wystawiona"),
    PAID        ("Zapłacona"),
    EXPIRED     ("Przedawniona"),
    DISPUTED    ("Spór"),
    CANCELLED   ("Anulowana"),
    CORRECTED   ("Skorygowana");

    private final String status;

    @JsonValue
    public String toValue() {
        return name().toLowerCase();
    }

    @JsonCreator
    public static InvoiceStatus forValue(String value) throws IllegalArgumentException {
        for (InvoiceStatus status : values()) {
            if (status.name().equalsIgnoreCase(value) || status.getStatus().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown InvoiceStatus: " + value);
    }
}
