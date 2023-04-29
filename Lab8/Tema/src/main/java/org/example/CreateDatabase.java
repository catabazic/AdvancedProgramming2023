package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class CreateDatabase {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "parola");
            stmt = conn.createStatement();
            String sql;
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "parola");
            stmt = conn.createStatement();
            sql = "DROP TABLE IF EXISTS album_genres;" +
                    "DROP TABLE IF EXISTS albums;" +
                    "DROP TABLE IF EXISTS genres;" +
                    "DROP TABLE IF EXISTS artists;" +
                    "CREATE TABLE artists (" +
                    "    id SERIAL PRIMARY KEY," +
                    "    name VARCHAR(255) NOT NULL" +
                    ");";
            stmt.executeUpdate(sql);
            sql = "CREATE TABLE genres (" +
                    "    id SERIAL  PRIMARY KEY," +
                    "    name VARCHAR(255) NOT NULL" +
                    ");";
            stmt.executeUpdate(sql);
            sql = "CREATE TABLE albums (" +
                    "    id SERIAL PRIMARY KEY," +
                    "    release_year INTEGER NOT NULL," +
                    "    title VARCHAR(255) NOT NULL," +
                    "    artist_id INTEGER NOT NULL," +
                    "    FOREIGN KEY (artist_id) REFERENCES artists(id)" +
                    ");";
            stmt.executeUpdate(sql);
            sql = "CREATE TABLE album_genres (" +
                    "    album_id INTEGER NOT NULL," +
                    "    genres_id1 INTEGER," +
                    "    genres_id2 INTEGER," +
                    "    genres_id3 INTEGER," +
                    "    genres_id4 INTEGER," +
                    "    genres_id5 INTEGER," +
                    "    FOREIGN KEY (album_id) REFERENCES albums(id)," +
                    "    FOREIGN KEY (genres_id1) REFERENCES genres(id)," +
                    "    FOREIGN KEY (genres_id2) REFERENCES genres(id)," +
                    "    FOREIGN KEY (genres_id3) REFERENCES genres(id)," +
                    "    FOREIGN KEY (genres_id4) REFERENCES genres(id)," +
                    "    FOREIGN KEY (genres_id5) REFERENCES genres(id)" +
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

