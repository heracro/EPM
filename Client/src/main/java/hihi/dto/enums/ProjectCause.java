package hihi.dto.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
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
