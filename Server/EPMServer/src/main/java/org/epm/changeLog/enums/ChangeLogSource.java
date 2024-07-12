package org.epm.changeLog.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.epm.common.enums.DeserializableEnum;

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
