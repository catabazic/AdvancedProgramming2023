package org.example;

import org.junit.Test;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


public class AnalyzeClass {

    public static void analyze(String path) {
        File file = new File(path);

        if (file.isDirectory()) {
            exploreFolder(file);
        } else if (file.isFile() && file.getName().endsWith(".jar")) {
            analyzeJar(file);
        } else if (file.isFile() && file.getName().endsWith(".class")) {
            analyzeClassFile(file);
        } else {
            throw new IllegalArgumentException("Invalid input: " + path);
        }
    }

    private static void exploreFolder(File folder) {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    exploreFolder(file);
                } else if (file.isFile() && file.getName().endsWith(".class")) {
                    analyzeClassFile(file);
                }
            }
        }
    }

    private static void analyzeJar(File jarFile) {
        try {
            URL jarURL = jarFile.toURI().toURL();
            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{jarURL});

            JarFile jar = new JarFile(jarFile);
            Enumeration<JarEntry> entries = jar.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                if (!entry.isDirectory() && entry.getName().endsWith(".class")) {
                    String className = entry.getName().replace("/", ".").substring(0, entry.getName().length() - 6);
                    analyzeClassName(jarFile.getAbsolutePath());
                }
            }
            jar.close();
        } catch (MalformedURLException e) {
            throw new RuntimeException("Failed to load jar file: " + jarFile, e);
        } catch (java.io.IOException e) {
            throw new RuntimeException("Error while exploring jar file: " + jarFile, e);
        }
    }

    private static boolean isTestClass(Class<?> clazz) {
        if (!Modifier.isPublic(clazz.getModifiers()) || Modifier.isAbstract(clazz.getModifiers())) {
            return false;
        }
        for (Method method : clazz.getMethods()) {
            if (method.isAnnotationPresent(Test.class)) {
                return true;
            }
        }
        return false;
    }

    private static void analyzeClassFile(File classFile) {
        analyzeClassName(classFile.getAbsolutePath());
    }

    public static void analyzeClassName(String urlClass) {
        try {
            File file = new File(urlClass);
            MyClassLoader myClassLoader = new MyClassLoader();
            if (file.exists()) {
                URL url = file.toURI().toURL();
                myClassLoader.addURL(url);
            }
            String className = getClassName(urlClass);
            Class myClass = Class.forName(className);

            System.out.println(myClass.getName());
            System.out.println(myClass.getPackage());
            System.out.println(Arrays.toString(myClass.getMethods()));
            System.out.println(myClass.getSuperclass());

            int passed = 0, failed = 0;
            for (Method method : Class.forName(myClass.getName()).getMethods()) {
                if (method.isAnnotationPresent(Test.class)) {
                    try {
                        method.invoke(null);
                        passed++;
                    } catch (Throwable ex) {
                        System.out.printf("Test %s failed: %s %n", method, ex.getCause());
                        failed++;
                    }
                }
            }
            System.out.printf("Passed: %d, Failed %d%n", passed, failed);
        } catch (ClassNotFoundException | MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getClassName(String classFilePath) {
        String separator = File.separator;
        if (separator.equals("\\")) {
            classFilePath = classFilePath.replace('/', '\\');
        }

        int packageStartIndex = classFilePath.indexOf("classes" + separator) + 8;
        int packageEndIndex = classFilePath.lastIndexOf(separator);
        String packagePath = classFilePath.substring(packageStartIndex, packageEndIndex).replace(separator, ".");

        String className = classFilePath.substring(packageEndIndex + 1, classFilePath.lastIndexOf(".class"));
        return packagePath + "." + className;
    }

}
