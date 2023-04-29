package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlbumDAO {
    public void create(int release_year, String title, String artist, String genre) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement("insert into albums (release_year, title, artist_id) values (?,?,?)")) {
            pstmt.setInt(1, release_year);
            pstmt.setString(2, title);
            if (!ArtistDAO.existsName(artist)) {
                ArtistDAO art = new ArtistDAO();
                art.create(artist);
            }
            pstmt.setInt(3, ArtistDAO.findByName(artist).ID);
            String query = pstmt.toString(); // get the SQL query
            System.out.println(query);
            pstmt.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try (PreparedStatement pstmt = con.prepareStatement("insert into album_genres (album_id, genres_id1, genres_id2, genres_id3, genres_id4, genres_id5) values (?,?,?,?,?,?)")) {
            pstmt.setInt(1, AlbumDAO.findByName(title).ID);
            String[] tokens = genre.split(",");
            int j = 2;
            for (int i = 0; i < 5; i++) {
                if (j < tokens.length + 2) {
                    if (!GenreDAO.existsName(tokens[i])) {
                        GenreDAO gen = new GenreDAO();
                        gen.create(tokens[i]);
                    }
                    pstmt.setInt(j, GenreDAO.findByName(tokens[i]).ID);
                } else {
                    pstmt.setNull(j, java.sql.Types.INTEGER);
                }
                j++;
            }
            pstmt.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Database.getConnection().close();
    }

    public static Album findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("select * from albums where title='" + name + "'")) {
            if (rs.next()) {
                try (PreparedStatement pstmt = con.prepareStatement("SELECT genres_id1, genres_id2, genres_id3, genres_id4, genres_id5 FROM album_genres WHERE album_id = ?")) {
                    pstmt.setInt(1, rs.getInt(1));
                    ResultSet rsG = pstmt.executeQuery();
                    String genres = new String();
                    if (rsG.next()) {
                        for (int i = 1; i <= 5; i++) {
                            int genreId = rsG.getInt(i);
                            if (genreId > 0) {
                                Genre genre = GenreDAO.findById(genreId);
                                genres += genre + ", ";
                            }
                        }
                    }
                    return new Album(rs.getInt(1), rs.getInt(2), name, rs.getString(4), genres);
                }finally {
                    con.close();
                }
            }
            return null;
        }finally {
            con.close();
        }
    }

    public static Album findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("select * from artists where id = " + id)) {
            if (rs.next()) {
                try (Statement stmtG = con.createStatement(); ResultSet rsG = stmt.executeQuery("select * from artists where album_id='" + rs.getInt(1) + "'")) {
                    String genres = new String();
                    for (int i = 2; i <= 6; i++) {
                        if (rsG.getString(i) != null) {
                            genres += rsG.getString(i) + ", ";
                        }
                    }
                    return new Album(id, rs.getInt(2), rs.getString(3), rs.getString(4), genres);
                }finally {
                    con.close();
                }
            }
            return null;
        }finally {
            con.close();
        }
    }

    public static List<Album> getFindAllQuery() {
        try {
            List<Album> albums = new ArrayList<>();
            Connection con = Database.getConnection();
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM albums";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                int year = rs.getInt(2);
                String artist = ArtistDAO.findById(rs.getInt(4)).NAME;
                String title = rs.getString(3);
                String sqlG = "Select * from album_genres where album_id=" + id;
                Statement stmtG = con.createStatement();
                ResultSet rsG = stmtG.executeQuery(sqlG);
                String genres = new String();
                if (rsG.next()) {
                    for (int i = 2; i <= 6; i++) {
                        int genreId = rsG.getInt(i);
                        if (genreId > 0) {
                            Genre genre = GenreDAO.findById(genreId);
                            genres += genre + ", ";
                        }
                    }
                }
                albums.add(new Album(id, year, title, artist, genres));
            }
            rs.close();
            stmt.close();
            con.close();
            return albums;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        List<Album> albums = AlbumDAO.getFindAllQuery();
        String toReturn = new String();
        for (int i = 0; i < albums.size(); i++) {
            toReturn += albums.get(i) + "\n";
        }
        return toReturn;
    }
}
