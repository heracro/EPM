package org.epm.project.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Random;

@Getter
@RequiredArgsConstructor
public enum LocationType {
    LOCAL,
    REMOTE_HTTP,
    REMOTE_HTTPS,
    REMOTE_FTP;

    public static LocationType randomLocationType() {
        Random random = new Random();
        return LocationType.values()[random.nextInt(LocationType.values().length)];
    }
}
