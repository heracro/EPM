package org.epm.common.configuration;

import org.epm.common.utils.DateUtils;

import java.time.LocalDate;

public class Config {

    public static final LocalDate BIG_BANG_DATE = LocalDate.parse("20-02-2022", DateUtils.DATE_FORMATTER);
    public static final LocalDate FAR_FAR_DATE = LocalDate.parse("24-02-2028", DateUtils.DATE_FORMATTER);
    public static final int DEFAULT_PAGE_SIZE = 20;

}
