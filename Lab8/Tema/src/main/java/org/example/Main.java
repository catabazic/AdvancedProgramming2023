package org.example;

import java.sql.SQLException;

public class Main {
    public static void main(String args[]) {
        try {
            /*var artists = new ArtistDAO();
            artists.create("Pink Floyd");
            artists.create("Michael Jackson");
            System.out.println(artists.getFindAllQuery());
            var genres = new GenreDAO();
            genres.create("Rock");
            genres.create("Funk");
            genres.create("Soul");
            genres.create("Pop");
            Database.getConnection().commit();*/
            var albums = new AlbumDAO();
           /* albums.create(1979, "The Wall", "Pink Floyd", "Rock");
            albums.create(1982, "Thriller", "Michael Jackson", "Funk,Soul,Pop");
            Database.getConnection().commit();*/
            //NewData.fromCVS("D:\\IT\\FII 2sem2\\PA\\Lab8\\albumlist.csv");

            System.out.println(albums);
            Database.getConnection().commit();
            Database.getConnection().close();
        } catch (SQLException e) {
            System.err.println(e);
            Database.rollback();
        }
    }

}