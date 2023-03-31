package org.example;

public class AddCommand implements Commands{
    /**
     * Vom adauga in catalogul c documentul d
     * @param c catalogul in care vom adauga
     * @param d documentul pe care il vom adauga
     */
    public static void run(Catalog c,Document d) {
        c.add(d);
    }
}
