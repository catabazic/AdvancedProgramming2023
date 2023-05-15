package org.example;

public class Board {
    int wasTried;
    private int[][] boardMatrix;
    private int[][] boardTry;

    Board() {
        boardMatrix = new int[15][15];
        boardTry = new int[15][15];
        wasTried = 0;
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                boardMatrix[i][j] = 0;
            }
        }
    }

    public int[][] tryMove(int x, int y, int color) {
        for(int i=0;i<15;i++){
            for(int j=0;j<15;j++){
                boardTry[i][j]=boardMatrix[i][j];
            }
        }
        if (x > 15 || y > 15 || x <= 0 || y <= 0) {
            return null;
        }
        if (boardTry[x-1][y-1] == 0) {
            boardTry[x-1][y-1] = color;
        } else {
            return null;
        }
        wasTried = 1;
        return boardTry;
    }

    public int doMove() {
        if (wasTried == 1) {
            boardMatrix = boardTry;
            wasTried = 0;
            return 0;
        } else {
            return 1;
        }
    }

    public int[][] getBoardMatrix() {
        return boardMatrix;
    }
}
