package hihi.content.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Slf4j
@Getter
@RequiredArgsConstructor
public enum Unit {
    PCS     ("szt."),
    PKG     ("p."),
    MASS_G  ("[g]"),
    MASS_KG ("[kg]"),
    LEN_MM  ("[mm]"),
    LEN_M   ("[m]"),
    AREA_MM ("[mm^2]"),
    AREA_M  ("[m^2]"),
    VOL_ML  ("[cm^3]"),
    VOL_L   ("[dm^3]");

    private final String unit;

    @JsonValue
    public String toValue() {
        return name();
    }

    @JsonCreator
    public static Unit of(String value) throws IllegalArgumentException {
        //log.info("Unit.of(value = {})", value);
        for (Unit unit : values()) {
            if (unit.name().equalsIgnoreCase(value) || unit.getUnit().equalsIgnoreCase(value)) {
                return unit;
            }
        }
        log.warn("No unit found for value \"{}\". Throwing IllegalArgumentException!", value);
        throw new IllegalArgumentException("Unknown Unit: " + value);
    }

    public static Unit randomUnit() {
        Random random = new Random();
        return Unit.values()[random.nextInt(Unit.values().length)];
    }

    public static Set<String> getValidInputStrings() {
        Set<String> result = new HashSet<>();
        for (Unit unit : values()) {
            result.add(unit.name().toLowerCase());
            result.add(unit.getUnit().toLowerCase());
        }
        return result;
    }
}
