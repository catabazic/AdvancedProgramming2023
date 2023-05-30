package com.bundle.info;

import java.util.Locale;

public class BundleInfo {

    private static String baseName;
    private static Locale locale;

    public BundleInfo() {
    }

    public static String getBaseName() {
        return baseName;
    }

    public static void setBaseName(String baseName) {
        BundleInfo.baseName = baseName;
    }

    public static Locale getLocale() {
        return locale;
    }

    public static void setLocale(Locale locale) {
        BundleInfo.locale = locale;
    }
}
