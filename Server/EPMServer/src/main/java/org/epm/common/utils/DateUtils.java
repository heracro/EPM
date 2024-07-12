package org.epm.common.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static LocalDate stringToLocalDate(String date) {
        return LocalDate.parse(date, DATE_FORMATTER);
    }

    public static String localDateToString(LocalDate date) {
        return date.format(DATE_FORMATTER);
    }

}
