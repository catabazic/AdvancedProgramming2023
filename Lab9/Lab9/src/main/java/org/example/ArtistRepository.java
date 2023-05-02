package org.example;

import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import java.util.List;

public class ArtistRepository {
    private EntityManager em;
    public List findByArtist(Artist artist) {
        return em.createNamedQuery("Album.findByArtist")
                .setParameter("artist", artist)
                .getResultList();
    }

    public void create(Artist artist) {
        em.getTransaction().begin();
        em.persist(artist);
        em.getTransaction().commit();
    }

    public Artist findById(int id) {
        return em.find(Artist.class, id);
    }
    public List<Artist> findByName(String name) {
        return em.createNamedQuery("Artist.findByName", Artist.class)
                .setParameter("name", "%" + name + "%")
                .getResultList();
    }
}
