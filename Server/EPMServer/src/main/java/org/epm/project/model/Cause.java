package org.epm.project.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Cause {
    HOBBY                   ("Hobby"),
    HOME_IMPROVEMENT        ("Usprawnienia domu"),
    ORDERED_COMMERCIAL      ("Zamówienie komercyjne"),
    ORDERED_NON_COMMERCIAL  ("Zamówienie po znajomości"),
    EDUCATIONAL             ("Projekt edukacyjny lub szkolny"),
    GIFT                    ("Prezent");

    private final String value;
}
