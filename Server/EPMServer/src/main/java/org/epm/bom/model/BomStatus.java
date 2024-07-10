package org.epm.bom.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.epm.project.model.enums.ProjectStatus;

import java.util.Random;

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

    public static BomStatus randomBomStatus(ProjectStatus status) {
        Random random = new Random();
        if (status == ProjectStatus.ONGOING || status == ProjectStatus.COMPLETED) {
            return BomStatus.TAKEN;
        } else {
            return BomStatus.values()[random.nextInt(BomStatus.values().length - 1)];
        }
    }
}
