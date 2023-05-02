package org.example;


import javax.persistence.*;

@Entity
@Table(name = "artists")
@NamedQueries({
        @NamedQuery(name = "Artist.findAll", query = "select e from Artist e order by e.name"),
        @NamedQuery(name = "Genre.findById", query = "SELECT e FROM Artist e where g.id=?1"),
        @NamedQuery(name = "Genre.findByName", query = "SELECT e FROM Artist e where g.name=?1")
})
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "id")
    @Column(name = "id")
    int id;
    @Column(name = "name")
    String name;

    public Artist() {
    }

    public Artist(String name) {
        this.name = name;
    }

    public Artist(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "[ " + this.id + ", " + this.name + "]";
    }


}