package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenreDAO implements DAO<Genre> {
    /**
     * Cream conexiunea cu bd si dupa selectam din baza de date genrul cu numele dat, si dupa caz returan true sau false
     *
     * @param name numele genrului
     * @return true in caz ca acets este in bd, false in caz contrar
     */
    public boolean existsName(String name) {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select * from genres where name='" + name + "'")) {
            return rs.next() ? true : false;
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
     * Cream conexiunea si cu ajutorul unui select vedem daca exista genrul dat sau nu.
     * In caz afirmativ, vom returna un obiect de tip Genre cu parametrii din bd.
     *
     * @param name numele genrului
     * @return genrul dat in caz ca exista, null in caz contrar
     * @throws SQLException
     */
    public Genre findByName(String name) {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select * from genres where name='" + name + "'")) {
            return rs.next() ? new Genre(rs.getInt(1), name) : null;
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
     * @param id id-ul genrului
     * @return genrului dat in caz ca exista, null in caz contrar
     * @throws SQLException
     */
    public Genre findById(int id) {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select * from genres where id = " + id)) {
            return rs.next() ? new Genre(id, rs.getString(2)) : null;
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
     * Selectam toate genrele din bd si ii adaugam intr o lista de Genre.
     *
     * @return o lista cu toate genrele din baza de date.
     * @throws SQLException
     */
    public List<Genre> getFindAllQuery() {
        List<Genre> genres = new ArrayList<>();
        int i = 0;
        Connection con = Database.getConnection();
        while (true) {
            try (Statement stmt = con.createStatement();
                 ResultSet rs = stmt.executeQuery("select * from (SELECT * FROM artists where rownum<=" + i + " order by rownum desc) where rownum=1")) {
                i++;
                if (rs.next()) {
                    genres.add(new Genre(rs.getInt("id"), rs.getString("name")));
                } else {
                    break;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return genres;
    }

    /**
     * Metoda folosita pentru a adauga in tabelul 'genres' date.
     * Initial cream conexiunea si dupa folosim 'insert into' pentru a insera in bd datele.
     *
     * @param name numele genrului
     */
    public void create(String name) {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into genres (name) values (?)")) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
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
