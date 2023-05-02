package org.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

public class NewData {

    /**
     * Functia folosita pentru a extrage date din afara.
     * fom folosi o clasa specializata cu ajutorul careia extragem fiecare data de care avem nevoie de pe fiecare rand.
     * dupa ce extragem toate datele necesare vom apela functia create() pentru a adauga date in tabele.
     *
     * @param url drumul catre fiesirul cvs din vare dorim sa extragem datele.
     */
    public static void fromCVS(String url) {
        try {
            CSVReader reader = new CSVReader(new FileReader(url));
            String[] header = reader.readNext();
            String[] line;
            while ((line = reader.readNext()) != null) {
                int year = Integer.valueOf(line[1]);
                String album = line[2].replace("'", "");
                String artist = line[3].replace("'", "");
                String genre = line[4].replace("'", "");
                String subgenre = line[5];

                AlbumDAO albumDao = new AlbumDAO();
                albumDao.create(year, album, artist, genre);
            }
            reader.close();
        } catch (CsvValidationException | SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }


}
