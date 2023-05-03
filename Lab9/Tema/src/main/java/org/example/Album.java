package org.example;

import javax.persistence.*;

@Entity
@Table(name = "albums")
@NamedQueries({
        @NamedQuery(name = "Album.findAll",
                query = "select e from Album e order by e.title"),
        @NamedQuery(name = "Album.findByArtist",
                query = "select e from Album e where e.artist = ?1"),
        })
public class Album {
    //name=titlu
    int RELEASE_YEAR;
    String ARTIST;
    String GENRES;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "id")
    @Column(name = "id")
    int ID;
    String NAME;

    public Album(){
    }
    public Album(int ID, int RELEASE_YEAR, String TITLE, String ARTIST, String GENRES) {
        this.ID = ID;
        this.NAME = TITLE;
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
    public int getId() {
        return ID;
    }

    public String getName() {
        return NAME;
    }


    @Override
    public String toString() {
        return "[ " + this.ID + ", " + this.RELEASE_YEAR + ", " + this.NAME + ", " + this.ARTIST + ", " + this.GENRES + "]";
    }
}
