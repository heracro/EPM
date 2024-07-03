package org.epm.material.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Unit {
    G   ("[g]"),
    PCS ("szt."),
    MM  ("[mm]"),
    VOL ("[cm^3]");

    private final String unit;

    @JsonValue
    public String toValue() {
        return name();
    }

    @JsonCreator
    public static Unit forValue(String value) throws IllegalArgumentException {
        for (Unit unit : values()) {
            if (unit.name().equalsIgnoreCase(value) || unit.getUnit().equalsIgnoreCase(value)) {
                return unit;
            }
        }
        throw new IllegalArgumentException("Unknown Unit: " + value);
    }
}
