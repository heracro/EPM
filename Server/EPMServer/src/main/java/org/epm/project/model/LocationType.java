package org.epm.project.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LocationType {
    LOCAL,
    REMOTE_HTTP,
    REMOTE_HTTPS,
    REMOTE_FTP
}
