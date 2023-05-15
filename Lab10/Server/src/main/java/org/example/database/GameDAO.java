package org.example.database;

import java.sql.*;

public class GameDAO {
    public void create(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement stmt = con.prepareStatement(
                "insert into games (board, turn) values (?, ?)")) {
            int[][] matrix = new int[15][15];
            for (int i = 0; i < 15; i++) {
                for (int j = 0; j < 15; j++) {
                    matrix[i][j] = 0;
                }
            }
            stmt.setString(1, String.valueOf(matrix));
            stmt.setInt(2, 0);
            stmt.executeUpdate();
        }
    }

    public Integer findByPlayer(int id) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id from artists where player_id1='" + id + "'")) {
            if (!rs.next()) {
                try (Statement stmt1 = con.createStatement();
                     ResultSet rs1 = stmt1.executeQuery(
                             "select id from artists where player_id2='" + id + "'")) {
                    if (!rs1.next()) {
                        return null;
                    } else {
                        return rs1.getInt(1);
                    }
                }
            } else {
                return rs.getInt(1);
            }
        }
    }

    public String findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id from games where id = " + id)) {
            return rs.next() ? rs.getString(2) : null;
        }
    }
}
