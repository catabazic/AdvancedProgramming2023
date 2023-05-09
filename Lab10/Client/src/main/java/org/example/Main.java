package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        GameClient client = new GameClient("127.0.0.1", 8100);
        try {
            client.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}