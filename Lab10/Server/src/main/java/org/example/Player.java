package org.example;

public class Player {
    private Game game;
    private int color;
    private BlitzTimer timer;

    public Player() {
        timer = new BlitzTimer(600);
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public BlitzTimer getTimer() {
        return timer;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

}
