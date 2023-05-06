package org.example;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "artists")
@NamedQueries({
        @NamedQuery(name = "Artist.findAll", query = "SELECT a FROM Artist a ORDER BY a.name"),
        @NamedQuery(name = "Artist.findById", query = "SELECT a FROM Artist a WHERE a.id = ?1"),
        @NamedQuery(name = "Artist.findByName", query = "SELECT a FROM Artist a WHERE a.name LIKE :name")
})
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "name")
    String name;

    @OneToMany(mappedBy = "artist")
    private List<Album> albums;

    public Artist() {
    }

    public Artist(String name) {
        this.name = name;
    }

    public Artist(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "[ " + this.id + ", " + this.name + " ]";
    }
}
