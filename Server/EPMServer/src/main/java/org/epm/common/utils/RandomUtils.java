package org.epm.common.utils;

import java.time.LocalDate;
import java.util.Random;

public class RandomUtils {

    private static final Random random = new Random();

    public static LocalDate randomDate(LocalDate from, LocalDate to) {
        if (!from.isBefore(to)) return from;
        long startEpochDay = from.toEpochDay();
        long endEpochDay = to.toEpochDay();
        long randomEpochDay = startEpochDay + random.nextInt((int) (endEpochDay - startEpochDay));
        return LocalDate.ofEpochDay(randomEpochDay);
    }

    public static int randomInt(int bound) {
        return random.nextInt(bound);
    }
}
