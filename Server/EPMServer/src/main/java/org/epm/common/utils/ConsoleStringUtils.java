package org.epm.common.utils;

import java.awt.*;

public class ConsoleStringUtils {

    public final static String reset = "\033[m";

    private static String buildString(String template, Object... args) {
        StringBuilder result = new StringBuilder(template);
        for (Object arg : args) {
            int index = result.indexOf("{}");
            if (index == -1) {
                break;
            }
            result.replace(index, index + 2, arg.toString());
        }
        return result.toString();
    }


    public static String bold(String template, Object... args) {
        return "\033[1m" + buildString(template, args) + "\033[22m";
    }
    public static String faint(String template, Object... args) {
        return "\033[2m" + buildString(template, args) + "\033[22m";
    }
    public static String italic(String template, Object... args) {
        return "\033[3m" + buildString(template, args) + "\033[23m";
    }
    public static String underline(String template, Object... args) {
        return "\033[4m" + buildString(template, args) + "\033[24m";
    }
    public static String blink(String template, Object... args) {
        return "\033[5m" + buildString(template, args) + "\033[25m";
    }
    public static String strike(String template, Object... args) {
        return "\033[9m" + buildString(template, args) + "\033[29m";
    }
    public static String fontColor(FontColor color, String template, Object... args) {
        return "\033[" + color.get() + "m" + buildString(template, args) + "\033[39m";
    }
    public static String fontColor(Color color, String template, Object... args) {
        return "\033[38;2;" + color.getRed() + ";" + color.getGreen() + ";"
                + color.getBlue() + "m" + buildString(template, args) + "\033[39m";
    }
    public static String bgColor(BackgroundColor color, String template, Object... args) {
        return "\033[" + color.get() + "m" + buildString(template, args) + "\033[49m";
    }
    public static String bgColor(Color color, String template, Object... args) {
        return "\033[48;2;" + color.getRed() + ";" + color.getGreen() + ";"
                + color.getBlue() + "m" + buildString(template, args) + "\033[49m";
    }

}
