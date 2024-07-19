package org.epm.common.utils;

import java.time.LocalDate;
import java.util.Random;

public class RandomUtils {

    private static final Random random = new Random();

    public static LocalDate randomDate(LocalDate from, LocalDate to) {
        if (!from.isBefore(to)) return from;
        return LocalDate.ofEpochDay(random.nextInt((int)from.toEpochDay(), (int)to.toEpochDay()));
    }

    public static int randomInt(int bound) {
        if (bound < 1) return 0;
        return random.nextInt(bound);
    }

    public static boolean randomBool() {
        return random.nextBoolean();
    }
}
