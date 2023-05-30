package com.commands;

import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.Currency;
import java.util.Locale;

public class SetLocale extends Command{
    private static Locale currentLocale;

    public SetLocale() {
    }

    public static void setCurrentLocale(String languageTag){
        currentLocale = Locale.forLanguageTag(languageTag);
    }

    public static Locale getCurrentLocale() {
        return currentLocale;
    }

    public static void setCurrentLocale(Locale currentLocale) {
        SetLocale.currentLocale = currentLocale;
    }

    @Override
    public void execute(String... params) {
        currentLocale = Locale.forLanguageTag(params[0]);
        DateFormatSymbols dateFormatSymbols = new DateFormatSymbols();
        System.out.println(Arrays.deepToString(dateFormatSymbols.getZoneStrings()));
        System.out.println(Arrays.toString(dateFormatSymbols.getWeekdays()));
        System.out.println(Arrays.toString(dateFormatSymbols.getMonths()));
        Currency currency = Currency.getInstance(currentLocale);
        System.out.println(currency.getCurrencyCode());
    }
}
