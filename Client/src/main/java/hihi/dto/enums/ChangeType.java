package hihi.dto.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ChangeType implements DeserializableEnum<ChangeType> {
    DOCUMENTATION ("Dokumentacja"),
    BOM ("Materiał (Bom)"),
    STATUS ("Status"),
    OTHER ("Inna zmiana");

    private final String value;

    @JsonCreator
    public static ChangeType of(String value) {
        return DeserializableEnum.of(value, ChangeType.class);
    }
}
