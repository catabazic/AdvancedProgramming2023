package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;

public class Test {
    public static void testJPA() {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("ExamplePU");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Artist artist = new Artist("Beatles");
        em.persist(artist);

        Artist a = (Artist)em.createQuery(
                        "select e from Artist e where e.name='Beatles'")
                .getSingleResult();
        a.setName("The Beatles");
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    public static void testLab8(){
        try {
            var artists = new ArtistDAO();
            var albums = new AlbumDAO();
            artists.create("Pink Floyd");
            artists.create("Michael Jackson");
            System.out.println(artists.getFindAllQuery());
            var genres = new GenreDAO();
            genres.create("Rock");
            genres.create("Funk");
            genres.create("Soul");
            genres.create("Pop");
            Database.getConnection().commit();
            albums.create(1979, "The Wall", "Pink Floyd", "Rock");
            albums.create(1982, "Thriller", "Michael Jackson", "Funk,Soul,Pop");
            Database.getConnection().commit();
            NewData.fromCVS("D:\\IT\\FII 2sem2\\PA\\Lab8\\albumlist.csv");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
