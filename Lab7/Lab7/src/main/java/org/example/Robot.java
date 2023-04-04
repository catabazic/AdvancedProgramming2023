package org.example;

import java.util.Random;

public class Robot  implements Runnable {
    private String name;
    private boolean running;
    private int row;
    private int col;
    Exploration explore;
    public Robot(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (running) {
            int[] newCell = explore.getMap().getNextCell(row, col);
            row = newCell[0];
            col = newCell[1];
            /*pick a new cell to explore*/
            explore.getMap().visit(row, col, this);
            // make the robot sleep for some time
            try {
                Thread.sleep(new Random().nextInt(5000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
