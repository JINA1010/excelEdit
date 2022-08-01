package com.daily.report.excel.edit.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public static String getFormatDate(String date, String format) {
        int year = Integer.parseInt(date.substring(0,4));
        int month = Integer.parseInt(date.substring(5,7));
        int day = Integer.parseInt(date.substring(8,10));

        LocalDate localDate = LocalDate.of(year, month, day);

        return localDate.format(DateTimeFormatter.ofPattern(format));
    }
}
