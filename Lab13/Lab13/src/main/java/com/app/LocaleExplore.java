package com.app;

import com.bundle.info.BundleInfo;
import com.commands.Command;
import com.commands.SetLocale;

import java.lang.reflect.InvocationTargetException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class LocaleExplore {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command = "";
        String baseName = "com.res.Messages";
        BundleInfo.setBaseName(baseName);
        SetLocale.setCurrentLocale(Locale.getDefault());
        while (true) {
            ResourceBundle resourceBundle = ResourceBundle.getBundle(BundleInfo.getBaseName(), SetLocale.getCurrentLocale());
            System.out.println(resourceBundle.getString("prompt"));

            command = scanner.nextLine();
            String[] comp = command.split(" ");
            if (comp[0].compareToIgnoreCase("exit") == 0) {
                break;
            }

            try {
                Class clazz = Class.forName("com." + comp[0]);
                Command command1 = (Command) clazz.getDeclaredConstructor().newInstance();
                command1.execute(comp[1]);
            } catch (ClassNotFoundException | NoSuchMethodException e) {
                System.out.println(resourceBundle.getString("invalid"));
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
