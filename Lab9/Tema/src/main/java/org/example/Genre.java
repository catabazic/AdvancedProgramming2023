package org.example;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "genres")
@NamedQueries({
        @NamedQuery(name = "Genre.findAll",
                query = "select e from Genre e order by e.name"),
})
public class Genre {
    public Genre() {
    }

    public Genre(int id, String name) {
        this.ID = id;
        this.NAME = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "id")
    @Column(name = "id")
    int ID;

    @Column(name = "name")
    String NAME;

    @ManyToMany(mappedBy = "genres")
    private List<Album> albums;

    public int getId() {
        return ID;
    }

    public String getName() {
        return NAME;
    }

    public void setId(int ID) {
        this.ID = ID;
    }

    public void setName(String NAME) {
        this.NAME = NAME;
    }

    @Override
    public String toString() {
        return "[ " + this.ID + ", " + this.NAME + "]";
    }
}
