package com.commands;

import com.bundle.info.BundleInfo;

import java.util.Locale;
import java.util.ResourceBundle;

public class DisplayLocales extends Command{

    private static Locale[] availableLocales;

    public DisplayLocales() {
        availableLocales = Locale.getAvailableLocales();
    }

    /*public static void displayLocales(){
        for(Locale locale : availableLocales){
            System.out.println(locale.getCountry() + " ~ " + locale.getLanguage());
        }
    }*/

    public static Locale[] getAvailableLocales() {
        return availableLocales;
    }

    public static void setAvailableLocales(Locale[] availableLocales) {
        DisplayLocales.availableLocales = availableLocales;
    }

    @Override
    public void execute(String... params) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(BundleInfo.getBaseName(), SetLocale.getCurrentLocale());
        System.out.println(resourceBundle.getString("locales"));
        for(Locale locale : availableLocales){
            System.out.println(locale.getCountry() + " ~ " + locale.getLanguage());
        }
    }
}
