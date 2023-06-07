package com.ltyy.nbascore.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DataUtils {

    public static String getCurrentDateFormat(long millis, String dateFormat) {//"yyyy-MM-dd HH:mm:ss"
        return new SimpleDateFormat(dateFormat, Locale.CHINA).format(new Date(millis));
    }
}
