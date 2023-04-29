package org.example;

public class Album extends Music {
    //name=titlu
    final int RELEASE_YEAR;
    final String ARTIST;
    final String GENRES;

    public Album(int ID, int RELEASE_YEAR, String TITLE, String ARTIST, String GENRES) {
        super(ID, TITLE);
        this.RELEASE_YEAR = RELEASE_YEAR;
        this.ARTIST = ARTIST;
        this.GENRES = GENRES;
    }

    public int getRELEASE_YEAR() {
        return RELEASE_YEAR;
    }

    public String getARTIST() {
        return ARTIST;
    }

    public String getGENRES() {
        return GENRES;
    }

    @Override
    public String toString() {
        return "[ " + this.ID + ", " + this.RELEASE_YEAR + ", " + this.NAME + ", " + this.ARTIST + ", " + this.GENRES + "]";
    }
}
