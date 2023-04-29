package org.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

public class NewData {

    public static void fromCVS(String url) {
        try {
            CSVReader reader = new CSVReader(new FileReader(url));
            String[] header = reader.readNext();
            String[] line;
            while ((line = reader.readNext()) != null) {
                int year = Integer.valueOf(line[1]);
                String album = line[2].replace("'","");
                String artist = line[3].replace("'","");
                String genre = line[4].replace("'","");
                String subgenre = line[5];

                AlbumDAO albumDao = new AlbumDAO();
                albumDao.create(year, album, artist, genre);
            }
            reader.close();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
