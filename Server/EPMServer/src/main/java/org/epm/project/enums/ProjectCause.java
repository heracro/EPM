package org.epm.project.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.epm.common.enums.DeserializableEnum;

@Getter
@RequiredArgsConstructor
public enum ProjectCause implements DeserializableEnum<ProjectCause> {
    HOBBY                   ("Hobby"),
    HOME_IMPROVEMENT        ("Usprawnienia domu"),
    ORDERED_COMMERCIAL      ("Zamówienie komercyjne"),
    ORDERED_NON_COMMERCIAL  ("Zamówienie po znajomości"),
    EDUCATIONAL             ("Projekt edukacyjny lub szkolny"),
    GIFT                    ("Prezent");

    private final String value;

    @JsonCreator
    public static ProjectCause of(String value) {
        return DeserializableEnum.of(value, ProjectCause.class);
    }

}
