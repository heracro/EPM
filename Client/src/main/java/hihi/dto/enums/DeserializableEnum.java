package hihi.dto.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public interface DeserializableEnum<T extends Enum<T> & DeserializableEnum<T>> {

    String getValue();

    @JsonCreator
    static <T extends Enum<T> & DeserializableEnum<T>> T of(String value, Class<T> enumType) {
        for (T enumConstant : enumType.getEnumConstants()) {
            if (enumConstant.name().equalsIgnoreCase(value) || enumConstant.getValue().equalsIgnoreCase(value)) {
                return enumConstant;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }

    @JsonValue
    default String toValue() {
        return getValue();
    }
}
