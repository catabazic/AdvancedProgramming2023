package org.example;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        var albums = new AlbumDAO();

        Test.testJPA();
        System.out.println(albums);

    }

}