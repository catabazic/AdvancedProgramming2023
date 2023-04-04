package org.example;

public class Robot  implements Runnable {
    private String name;
    private boolean running;
    Exploration explore;
    public Robot(String name) {
        this.name = name;
    }
//...
    public void run() {
        while (running) {
            /*pick a new cell to explore*/
            explore.getMap().visit(row, col, this);
            /*make the robot sleep for some time*/
        }
    }
}
