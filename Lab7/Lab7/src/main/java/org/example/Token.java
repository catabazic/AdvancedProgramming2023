package org.example;

public class Token {
    private final int number;

    Token(int n) {
        number = n;
    }

    public int getNumber() {
        return number;
    }

    public boolean isEmpty() {
        return false;
    }
//toString
}