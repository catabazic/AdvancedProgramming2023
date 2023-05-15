package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GameServer {
    public static final int PORT = 8100;
    static final Lock printWriterLock = new ReentrantLock();
    private static Player playerWait;

    public GameServer() throws IOException {

        // Create a socket to listen to
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            while (true) {
                System.out.println("The server is waiting for connections");
                // Wait until a connection is made, then accept it.
                Socket socket = serverSocket.accept();
                if (true) {
                    new ClientThread(socket).start();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert serverSocket != null;
            serverSocket.close();
        }
    }

    public static int addPlayer(Player player) {
        if (playerWait != null) {
            player.setGame(playerWait.getGame());
            playerWait = null;
            return 1;
        } else {
            player.setGame(new Game());
            playerWait = player;
            System.out.println("You should wait another player to start the game!");
            return 0;
        }
    }
}
