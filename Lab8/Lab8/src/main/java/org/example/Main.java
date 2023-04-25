package org.example;

import java.sql.SQLException;

public class Main {
    public static void main(String args[]) {
        try {
            var artists = new ArtistDAO();
            artists.create("Pink Floyd");
            artists.create("Michael Jackson");
            var genres = new GenreDAO();
            genres.create("Rock"); //TODO: Funk, Soul, Pop
            Database.getConnection().commit();
            var albums = new AlbumDAO();
            albums.create(1979, "The Wall", "Pink Floyd", "Rock");
            //findByName
            albums.create(1982, "Thriller", "Michael Jackson","Funk,Soul,Pop");
                    Database.getConnection().commit();
            //TODO: print all the albums in the database
            Database.getConnection().close();
        } catch (SQLException e) {
            System.err.println(e);
            Database.rollback();
        }
    }

}