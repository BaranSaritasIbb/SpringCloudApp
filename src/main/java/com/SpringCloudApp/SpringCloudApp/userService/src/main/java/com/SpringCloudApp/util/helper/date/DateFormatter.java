package com.SpringCloudApp.util.helper.date;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {

    private static final String DEFAULT_PATTERN = "yyyy-MM-dd";

    public static String format(Date date) {
        return format(date, DEFAULT_PATTERN);
    }

    public static String format(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
}
