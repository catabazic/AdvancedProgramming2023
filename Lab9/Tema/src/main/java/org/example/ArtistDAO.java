package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtistDAO implements DAO<Artist> {
    /**
     * Cream conexiunea cu bd si dupa selectam din baza de date artistul cu numele dat, si dupa caz returan true sau false
     *
     * @param name numele artistului
     * @return true in caz ca acets este in bd, false in caz contrar
     */
    public boolean existsName(String name) {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select * from artists where name='" + name + "'")) {
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Cream conexiunea si cu ajutorul unui select vedem daca exista artistul dat sau nu.
     * In caz afirmativ, vom returna un obiect de tip Artist cu parametrii din bd.
     *
     * @param name numele artistului
     * @return artistul dat in caz ca exista, null in caz contrar
     * @throws SQLException
     */
    public Artist findByName(String name) {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select * from artists where name='" + name + "'")) {
            return rs.next() ? new Artist(rs.getInt(1), name) : null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Similar ca la findByName, doar ca in functie de id
     *
     * @param id id-ul artistului
     * @return artistul dat in caz ca exista, null in caz contrar
     * @throws SQLException
     */
    public Artist findById(int id) {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select * from artists where id = " + id)) {
            return rs.next() ? new Artist(id, rs.getString(2)) : null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Selectam toti artistii din bd si ii adaugam intr o lista de Artist.
     *
     * @return o lista cu toti artistii din baza de date.
     * @throws SQLException
     */
    public List<Artist> getFindAllQuery() {
        try {
            List<Artist> artists = new ArrayList<>();
            Connection con = Database.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM artists";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println(rs.getString(2));
                artists.add(new Artist(rs.getInt(1), rs.getString(2)));
            }
            rs.close();
            stmt.close();
            con.close();
            return artists;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metoda folosita pentru a adauga in tabelul 'artists' date.
     * Initial cream conexiunea si dupa folosim 'insert into' pentru a insera in bd datele.
     *
     * @param name numele artistului
     */
    public void create(String name) {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into artists (name) values (?)")) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
            System.out.println(pstmt);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                con.commit();
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}