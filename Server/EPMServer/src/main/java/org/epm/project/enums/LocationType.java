package org.epm.project.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.epm.common.enums.DeserializableEnum;

@Getter
@RequiredArgsConstructor
public enum LocationType implements DeserializableEnum<LocationType> {
    LOCAL ("Lokalny"),
    REMOTE_HTTP ("Zdalny http"),
    REMOTE_HTTPS ("Zdalny https"),
    REMOTE_FTP ("Zdalny ftp"),
    SHELF ("Papierowy");

    private final String value;

    @JsonCreator
    public static LocationType of(String value) {
        return DeserializableEnum.of(value, LocationType.class);
    }
}
