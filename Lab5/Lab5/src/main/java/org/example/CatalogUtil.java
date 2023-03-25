package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class CatalogUtil {
    public static void save(Catalog catalog, String path)
            throws IOException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File(path), catalog.toString());
        }catch (IOException e){
            System.err.println("Eroare la salvare!");
        }
    }
    public static Catalog load(String path)
            throws InvalidCatalogException, IOException {
        Catalog catalog = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            catalog = objectMapper.readValue(new File(path), Catalog.class);
        } catch (IOException e) {
            System.err.println("Eroare la deschidere fisier");
        } finally {
            return catalog;
        }
    }
}
