package org.epm.project.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Random;

@Getter
@RequiredArgsConstructor
public enum ProjectCause {
    HOBBY                   ("Hobby"),
    HOME_IMPROVEMENT        ("Usprawnienia domu"),
    ORDERED_COMMERCIAL      ("Zamówienie komercyjne"),
    ORDERED_NON_COMMERCIAL  ("Zamówienie po znajomości"),
    EDUCATIONAL             ("Projekt edukacyjny lub szkolny"),
    GIFT                    ("Prezent");

    private final String value;

    public static ProjectCause randomProjectCause() {
        Random random = new Random();
        return ProjectCause.values()[random.nextInt(ProjectCause.values().length)];
    }
}
