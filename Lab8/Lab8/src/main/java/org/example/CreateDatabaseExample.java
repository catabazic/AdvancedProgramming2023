package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class CreateDatabaseExample {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://HOME-PC:8080/", "myuser", "mypassword");
            stmt = conn.createStatement();
            String sql = "CREATE DATABASE mydatabase";
            stmt.executeUpdate(sql);
            System.out.println("Database created successfully...");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mydatabase", "myuser", "mypassword");
            stmt = conn.createStatement();
            sql = "CREATE TABLE artists (" +
                    "    id SERIAL PRIMARY KEY," +
                    "    name VARCHAR(255) NOT NULL" +
                    ");";
            stmt.executeUpdate(sql);
            sql = "CREATE TABLE genres (" +
                    "    id SERIAL PRIMARY KEY," +
                    "    name VARCHAR(255) NOT NULL" +
                    ");";
            stmt.executeUpdate(sql);
            sql = "CREATE TABLE albums (" +
                    "    id SERIAL PRIMARY KEY," +
                    "    release_year INTEGER NOT NULL," +
                    "    title VARCHAR(255) NOT NULL," +
                    "    artist VARCHAR(255) NOT NULL," +
                    "    genre_ids INTEGER[] NOT NULL" +
                    ");";
            stmt.executeUpdate(sql);
            sql = "CREATE TABLE album_genres (" +
                    "    album_id INTEGER NOT NULL," +
                    "    genre_id INTEGER NOT NULL," +
                    "    PRIMARY KEY (album_id, genre_id)," +
                    "    FOREIGN KEY (album_id) REFERENCES albums(id)," +
                    "    FOREIGN KEY (genre_id) REFERENCES genres(id)" +
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

