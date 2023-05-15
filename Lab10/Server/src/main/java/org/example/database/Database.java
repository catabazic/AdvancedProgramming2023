package org.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:postgresql://localhost:5432/albums";
    private static final String USER = "music_library";
    private static final String PASSWORD = "password";
    private static Connection connection = null;

    private Database() {
    }

    public static Connection getConnection() {
        createConnection();
        return connection;
    }

    private static void createConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public static void closeConnection() throws SQLException {
        connection.close();
    }

    public static void rollback(){
        try {
            connection.rollback();
        }catch (SQLException e){
            System.err.println(e);
        }
    }

}