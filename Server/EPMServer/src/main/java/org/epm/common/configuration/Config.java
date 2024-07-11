package org.epm.common.configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Config {

    public static boolean PREFER_VERBOSE_METHODS = true;

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public static final LocalDate BIG_BANG_DATE = LocalDate.parse("20-02-2002", DATE_FORMATTER);
    public static final LocalDate FAR_FAR_DATE = LocalDate.parse("24-02-2062", DATE_FORMATTER);

    public static final int DELIVERIES_PER_PAGE = 30;
    public static final int MATERIALS_PER_PAGE = 30;
    public static final int PROJECTS_PER_PAGE = 15;
    public static final int BOMS_PER_PAGE = 50;

    public static LocalDate stringToLocalDate(String date) {
        return LocalDate.parse(date, DATE_FORMATTER);
    }

    public static String localDateToString(LocalDate date) {
        return date.format(DATE_FORMATTER);
    }
}
