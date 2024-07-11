package org.epm.project.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Random;

@Getter
@RequiredArgsConstructor
public enum ProjectStatus {
    PLANNED,
    AWAITING_MATERIALS,
    READY,
    ONGOING,
    COMPLETED;

    public static ProjectStatus randomProjectStatus() {
        Random random = new Random();
        return ProjectStatus.values()[random.nextInt(ProjectStatus.values().length)];
    }
}
