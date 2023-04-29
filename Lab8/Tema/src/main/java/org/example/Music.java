package org.example;

public abstract class Music {
    final int ID;
    final String NAME;

    public Music(int id, String name) {
        this.ID = id;
        this.NAME = name;
    }

    public int getId() {
        return ID;
    }

    public String getName() {
        return NAME;
    }

    @Override
    public String toString() {
        return "[ " + this.ID + ", " + this.NAME + "]";
    }
}
