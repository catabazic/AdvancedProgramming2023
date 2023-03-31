package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class SaveCommand implements Commands{
    /**
     * Folosim clasa ObjectMapper pentru a scrie in fisier continutul catalogului
     * @param catalog Catalogul pe care dorim sa il salvam
     * @param path Calea catre locul unde va fi salvat
     */
    public static void run(Catalog catalog, String path){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File(path), catalog.toString());
        }catch (IOException e){
            System.err.println("Eroare la salvare!");
        }
    }
}
