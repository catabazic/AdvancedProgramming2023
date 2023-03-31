package org.example;

public class ListCommand implements Commands{
    /**
     * o functia pentru a afisa informatiile despre un catalog
     * @param c Catalogul pe care il vom afisa
     */
    public static void run(Catalog c) {
        System.out.println(c);
    }
}
