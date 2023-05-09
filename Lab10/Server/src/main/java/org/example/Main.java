package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            GameServer s = new GameServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}