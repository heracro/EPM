package org.epm.project.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.epm.common.enums.DeserializableEnum;

@Getter
@RequiredArgsConstructor
public enum ProjectStatus implements DeserializableEnum<ProjectStatus> {
    PLANNED ("Zaplanowany"),
    AWAITING_MATERIALS ("Oczekuje na materiały"),
    READY ("Gotowy do rozpoczęcia"),
    ONGOING ("Trwa"),
    COMPLETED ("Zakończony"),
    CANCELLED ("Anulowany");

    private final String value;

    @JsonCreator
    public static ProjectStatus of(String value) {
        return DeserializableEnum.of(value, ProjectStatus.class);
    }

}
