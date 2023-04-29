package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenreDAO {
    public static boolean existsName(String name) {
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

    public static Genre findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select * from genres where name='" + name + "'")) {
            return rs.next() ? new Genre(rs.getInt(1), name) : null;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static Genre findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select * from genres where id = " + id)) {
            return rs.next() ? new Genre(id, rs.getString(2)) : null;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static List<Genre> getFindAllQuery() {
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

    public void create(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into genres (name) values (?)")) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            con.commit();
            con.close();
        }

    }

}
