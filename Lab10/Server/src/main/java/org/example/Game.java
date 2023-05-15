package org.example;

public class Game {
    private int turn; // 1-alb; 2-negru
    private int winner;
    private Player player1;
    private Player player2;
    private Board board;
    private int players;

    Game() {
        turn = 0;
        winner = 0;
        players = 0;
        board = new Board();
    }

    public synchronized int getTurn() {
        return turn;
    }

    public synchronized void startGame(Player player) {
        players++;
        if (players == 2) {
            player2 = player;
            turn = 1;
        } else {
            player1 = player;
        }
    }

    public synchronized int doMove() {
        if (board.doMove() == 0) {
            if (turn == 1) {
                player1.getTimer().stopTimer();
                player2.getTimer().startTimer();
                turn = 2;
                notify();
            } else {
                player2.getTimer().stopTimer();
                player1.getTimer().startTimer();
                turn = 1;
            }
            if (didSomeoneWin()) {
                return 2;
            }
            return 0;
        } else {
            return 1;
        }
    }

    public synchronized int[][] tryMove(int x, int y) {
        return board.tryMove(x, y, turn);
    }

    public synchronized Player getPlayer1() {
        return player1;
    }

    public synchronized void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public synchronized Player getPlayer2() {
        return player2;
    }

    public synchronized void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public synchronized int getPlayers() {
        return players;
    }

    public String getMatrix() {
        String matrix = new String();
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                matrix += board.getBoardMatrix()[i][j] + " ";
            }
            matrix += "\n";
        }
        return matrix;
    }

    public boolean didSomeoneWin() {
        if (checkFive(turn)) {
            winner = turn;
            return true;
        }
        if (player1.getTimer().getTimeLeft() == 0) {
            winner = 2;
            return true;
        }
        if (turn == 2 && player2.getTimer().getTimeLeft() == 0) {
            winner = 1;
            return true;
        }

        return false;
    }

    public boolean checkFive(int m) {
        int rows = board.getBoardMatrix().length;
        int cols = board.getBoardMatrix()[0].length;
        // check rows
        for (int i = 0; i < rows; i++) {
            int count = 0;
            for (int j = 0; j < cols; j++) {
                if (board.getBoardMatrix()[i][j] == m) {
                    count++;
                    if (count == 5 && board.getBoardMatrix()[i][j + 1] != m) {
                        return true;
                    }
                } else {
                    count = 0;
                }
            }
        }
        // check columns
        for (int j = 0; j < cols; j++) {
            int count = 0;
            for (int i = 0; i < rows; i++) {
                if (board.getBoardMatrix()[i][j] == m) {
                    count++;
                    if (count == 5 && board.getBoardMatrix()[i + 1][j] != m) {
                        return true;
                    }
                } else {
                    count = 0;
                }
            }
        }
        // check diagonals
        for (int i = 0; i < rows - 4; i++) {
            for (int j = 0; j < cols - 4; j++) {
                if (board.getBoardMatrix()[i][j] == m &&
                        board.getBoardMatrix()[i + 1][j + 1] == m &&
                        board.getBoardMatrix()[i + 2][j + 2] == m &&
                        board.getBoardMatrix()[i + 3][j + 3] == m &&
                        board.getBoardMatrix()[i + 4][j + 4] == m) {
                    return true;
                }
            }
        }
        for (int i = 0; i < rows - 4; i++) {
            for (int j = 4; j < cols; j++) {
                if (board.getBoardMatrix()[i][j] == m &&
                        board.getBoardMatrix()[i + 1][j - 1] == m &&
                        board.getBoardMatrix()[i + 2][j - 2] == m &&
                        board.getBoardMatrix()[i + 3][j - 3] == m &&
                        board.getBoardMatrix()[i + 4][j - 4] == m) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getWinner() {
        return winner;
    }
}
