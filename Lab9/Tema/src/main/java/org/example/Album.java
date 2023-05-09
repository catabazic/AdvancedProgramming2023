package org.example;

import javax.persistence.*;
import java.util.List;
import javax.persistence.PersistenceUnit;


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
    @Column(name = "release_year")
    int RELEASE_YEAR;
    @Column(name = "artist")
    String ARTIST;
    String GENRES;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "id")
    @Column(name = "id")
    int ID;
    @Column(name = "title")
    String NAME;
    @ManyToMany
    @JoinTable(
            name = "album_genres",
            joinColumns = { @JoinColumn(name = "album_id") },
            inverseJoinColumns = { @JoinColumn(name = "genres_id") }
    )
    private List<Genre> genres;
    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    public Album(){
    }
    public Album(int ID, int RELEASE_YEAR, String TITLE, String ARTIST, String GENRES) {
        this.ID = ID;
        this.NAME = TITLE;
        this.RELEASE_YEAR = RELEASE_YEAR;
        this.ARTIST = ARTIST;
        this.GENRES = GENRES;
    }

    public int getRelease_year() {
        return RELEASE_YEAR;
    }

    public String getArtist() {
        return ARTIST;
    }

    public String getGenres() {
        return GENRES;
    }
    public int getId() {
        return ID;
    }

    public String getName() {
        return NAME;
    }

    public int getIs() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "[ " + this.ID + ", " + this.RELEASE_YEAR + ", " + this.NAME + ", " + this.ARTIST + ", " + this.GENRES + "]";
    }
}
