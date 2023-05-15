package org.example.database;

import java.sql.*;

public class PlayerDAO {
    public void create(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement stmt = con.prepareStatement(
                "insert into artists (name) values (?)")) {
            stmt.setString(1, name);
            stmt.executeUpdate();
        }
    }
    public Integer findByGame(int id) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id from artists where game_id='" + id + "'")) {
            return rs.next() ? rs.getInt(1) : null;
        }
    }

    public Integer findByGameAndColor(int id, int color) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id from players where game_id='" + id + "' and color='" + color + "'")) {
            return rs.next() ? rs.getInt(1) : null;
        }
    }
    public String findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id from players where id = " + id )) {
            return rs.next() ? rs.getString(2) : null;
        }
    }

}
