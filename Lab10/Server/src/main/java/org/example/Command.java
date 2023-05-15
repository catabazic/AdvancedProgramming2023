package org.example;

import static java.lang.Thread.sleep;

public class Command {
    public static void createGame() {
    }

    public synchronized static String joinGame(Player player) {
        if (GameServer.addPlayer(player) == 1) {
            return "The Game is ready!";
        } else {
            return "You should wait another player to start the game!";
        }
    }

    public static String submitMove(Player player) {
        int temp = player.getGame().doMove();
        if (temp == 0) {
            return "Move was submitted!";
        } else if (temp == 1) {
            return "You didn't tried yet!";
        } else {
            return "Game is finished!";
        }
    }

    public synchronized static String tryMove(Player player, int x, int y) {
        int[][] matrix = player.getGame().tryMove(x, y);
        String matrice = new String();
        if (matrix != null) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    matrice += matrix[i][j] + " ";
                }
                matrice += "\n";
            }
        } else {
            return "Invalid parameters or the cell is already taken!";
        }
        return matrice;
    }

    public String startGame(Player player) {
        if (player.getGame().getPlayers() == 0) {
            player.setColor(1);
        } else {
            player.setColor(2);
        }
        player.getGame().startGame(player);
        while (player.getGame().getTurn() == 0) {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        synchronized (GameServer.printWriterLock) {
            if (player.getColor() == 1) {
                player.getTimer().startTimer();
            }
            return "Game started!";
        }
    }
}
