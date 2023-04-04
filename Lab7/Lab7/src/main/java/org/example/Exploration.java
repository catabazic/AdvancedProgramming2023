package org.example;

import java.util.ArrayList;
import java.util.List;

public class Exploration {
    private final SharedMemory mem = new SharedMemory();
    private final ExplorationMap map = new ExplorationMap();
    private final List<Robot> robots = new ArrayList<>();
//...

    public ExplorationMap getMap() {
        return map;
    }
    public void start() {
        for (Robot robot : robots) {
            /*start a new Thread representing the robot;*/
        }
    }
    public static void main(String args[]) {
        var exlore = new Exploration();
        explore.addRobot(new Robot("Wall-E"));
        explore.addRobot(new Robot("R2D2"));
        explore.addRobot(new Robot("Optimus Prime"));
        exlore.start();
    }

}
