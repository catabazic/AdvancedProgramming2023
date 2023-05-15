package org.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class CreateDatabase {
    /**
     * Clasa in care cream tabelele cu care vom lucra acest laborator
     * Ne conectam la baza de date din postgresql si folosim clase destinate pentru lucrul cu baza de date.
     * Prin urmare, cream fiecare tabel in parte.
     *
     * @param args
     */
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            String sql;
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "parola");
            stmt = conn.createStatement();
            sql = "DROP TABLE IF EXISTS players;" +
                    "DROP TABLE IF EXISTS games;";
            stmt.executeUpdate(sql);
            sql = "CREATE TABLE players (" +
                    "    id SERIAL PRIMARY KEY," +
                    "    game_id int," +
                    "    color int" +
                    ");";
            stmt.executeUpdate(sql);
            sql = "CREATE TABLE timer (" +
                    "    id SERIAL PRIMARY KEY," +
                    "    time_left int," +
                    "    player_id int," +
                    "    FOREIGN KEY (player_id) REFERENCES players(id)" +
                    ");";
            stmt.executeUpdate(sql);

            sql = "CREATE TABLE games (" +
                    "    id SERIAL PRIMARY KEY," +
                    "    player_id1 int," +
                    "    player_id2 int," +
                    "    board varchar(90)," +
                    "    turn INT," +
                    "    winner int," +
                    "    FOREIGN KEY (player_id1) REFERENCES players(id)," +
                    "    FOREIGN KEY (player_id2) REFERENCES players(id)," +
                    "    FOREIGN KEY (winner) REFERENCES players(id)" +
                    ");";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

