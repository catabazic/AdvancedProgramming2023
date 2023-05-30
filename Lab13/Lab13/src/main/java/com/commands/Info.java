package com.commands;

import com.bundle.info.BundleInfo;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class Info extends Command {

    public Info() {
    }

    /*public static void displayInfoAboutCurrLocale() {
        System.out.println(SetLocale.getCurrentLocale().getCountry() + " ~ "
                + SetLocale.getCurrentLocale().getLanguage());
    }

    public static void displayInfoAboutLocale(Locale locale) {
        System.out.println(locale.getCountry() + " ~ " + locale.getLanguage());
    }*/

    @Override
    public void execute(String... params) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(BundleInfo.getBaseName(), SetLocale.getCurrentLocale());
        if (params[0] == null) {
            String pattern = resourceBundle.getString("locale.set");
            Object[] arguments = {
                    SetLocale.getCurrentLocale().getCountry() + " ~ " +
                            SetLocale.getCurrentLocale().getLanguage()
            };
            String message = new MessageFormat(pattern).format(arguments);
            System.out.println(message);
        } else {
            Locale locale = Locale.forLanguageTag(params[0]);
            String pattern = resourceBundle.getString("info");
            Object[] arguments = {
                    locale.getCountry() + " ~ " + locale.getLanguage()
            };
            String message = new MessageFormat(pattern).format(arguments);
            System.out.println(message);
        }
    }
}
