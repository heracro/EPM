package hihi.dto.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ChangeLogSource implements DeserializableEnum<ChangeLogSource> {
    USER ("Użytkownik"),
    SYSTEM ("System");

    private final String value;

    @JsonCreator
    public static ChangeLogSource of(String value) {
        return DeserializableEnum.of(value, ChangeLogSource.class);
    }
}
