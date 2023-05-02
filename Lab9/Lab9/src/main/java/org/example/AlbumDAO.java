package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlbumDAO {
    /**
     * Functia folosita pentru a gasi un album dupa denumirea sa.
     * Cautam in baza de date albumul cu numele dat si in caz ca nu exista nici o instalta vom returna null.
     * In caz contrar com selecta si toate genurile de muzica din bd si le vom adauga intr un string.
     * La final, returnam un album cu toti parametrii obtinute din baza de date
     *
     * @param name denumirea albumului
     * @return albumul cerut
     * @throws SQLException
     */
    public static Album findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("select * from albums where title='" + name + "'")) {
            if (rs.next()) {
                try (PreparedStatement pstmt = con.prepareStatement("SELECT genres_id1, genres_id2, genres_id3, genres_id4, genres_id5 FROM album_genres WHERE album_id = ?")) {
                    pstmt.setInt(1, rs.getInt(1));
                    ResultSet rsG = pstmt.executeQuery();
                    String genres = new String();
                    GenreDAO genreDAO = new GenreDAO();
                    if (rsG.next()) {
                        for (int i = 1; i <= 5; i++) {
                            int genreId = rsG.getInt(i);
                            if (genreId > 0) {
                                Genre genre = genreDAO.findById(genreId);
                                genres += genre + ", ";
                            }
                        }
                    }
                    return new Album(rs.getInt(1), rs.getInt(2), name, rs.getString(4), genres);
                } finally {
                    con.close();
                }
            }
            return null;
        } finally {
            con.close();
        }
    }

    /**
     * Functie folosita pentru a returna toate albumele existente in baza de date.
     * Selectam toate albumele din baza de date, si, printr un while iteram fiecare rand obtinut.
     * In while selectam toate genurile de muzica pentru id-ul albumului curent si le punem intr un string.
     * Prin urmare adaugam un album nou in lista cu toate datele obtinute. Repetam pentru fiecare rand.
     *
     * @return o lista cu toate albumele din bd.
     */
    public static List<Album> getFindAllQuery() {
        try {
            List<Album> albums = new ArrayList<>();
            Connection con = Database.getConnection();
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM albums";
            ResultSet rs = stmt.executeQuery(sql);
            GenreDAO genreDAO = new GenreDAO();
            ArtistDAO artistDAO = new ArtistDAO();
            while (rs.next()) {
                int id = rs.getInt(1);
                int year = rs.getInt(2);
                String artist = artistDAO.findById(rs.getInt(4)).name;
                String title = rs.getString(3);
                String sqlG = "Select * from album_genres where album_id=" + id;
                Statement stmtG = con.createStatement();
                ResultSet rsG = stmtG.executeQuery(sqlG);
                String genres = new String();
                if (rsG.next()) {
                    for (int i = 2; i <= 6; i++) {
                        int genreId = rsG.getInt(i);
                        if (genreId > 0) {
                            Genre genre = genreDAO.findById(genreId);
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

    /**
     * Metoda folosita pentru a adauga in tabelul 'albums' si 'album_genres' date.
     * Initial cream conexiunea si dupa folosim 'insert into' pentru a insera in bd datele.
     * In caz ca numele unui artist nu este in bd, il adaugam si pe acesta.
     * Dupa ce adaugam in tabelul 'albums' toate datele, separam genres in tokens si il adaugam pe rand in tabel.
     * Este posibil sa adaugam maximum 5 genre de muzica, daca sunt mai putine setam celelalte ca fiind null.
     * Similar ca la artisti, in caz ca un genru nu exista il vom adauga in tabel
     *
     * @param release_year anul in care a fost lansat
     * @param title        denumirea albuului
     * @param artist       numele artistului
     * @param genre        genu de muzica
     * @throws SQLException
     */
    public void create(int release_year, String title, String artist, String genre) throws SQLException {
        Connection con = Database.getConnection();
        ArtistDAO artDAO = new ArtistDAO();
        try (PreparedStatement pstmt = con.prepareStatement("insert into albums (release_year, title, artist_id) values (?,?,?)")) {
            pstmt.setInt(1, release_year);
            pstmt.setString(2, title);
            if (!artDAO.existsName(artist)) {
                ArtistDAO art = new ArtistDAO();
                art.create(artist);
            }
            pstmt.setInt(3, artDAO.findByName(artist).id);
            String query = pstmt.toString();
            System.out.println(query);
            pstmt.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try (PreparedStatement pstmt = con.prepareStatement("insert into album_genres (album_id, genres_id1, genres_id2, genres_id3, genres_id4, genres_id5) values (?,?,?,?,?,?)")) {
            pstmt.setInt(1, AlbumDAO.findByName(title).ID);
            String[] tokens = genre.split(",");
            GenreDAO genreDAO = new GenreDAO();
            int j = 2;
            for (int i = 0; i < 5; i++) {
                if (j < tokens.length + 2) {
                    if (!genreDAO.existsName(tokens[i])) {
                        GenreDAO gen = new GenreDAO();
                        gen.create(tokens[i]);
                    }
                    pstmt.setInt(j, genreDAO.findByName(tokens[i]).ID);
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
