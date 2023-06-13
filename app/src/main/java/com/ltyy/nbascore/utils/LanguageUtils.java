package com.ltyy.nbascore.utils;

import java.util.Locale;

public class LanguageUtils {
    public static boolean isChineseLanguage() {
        Locale locale = Locale.getDefault();
        String language = locale.getLanguage();
        return language.equals("zh");
    }
}
