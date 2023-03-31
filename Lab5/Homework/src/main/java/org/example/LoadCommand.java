package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class LoadCommand implements Commands{
    /**
     * O functie pentru a returna catalogul care este stocat in format jsone
     * vom initializa catalog cu null, in caz ca apare o exceptie
     * Vom folosi clasa objectMapper pentrua extrage din fisier datele in un obiect de tip catalog
     * @param path drumul catre fisierul json care are informatiile despre un catalog
     * @return catalogul din fisier in caz ca nu apare nici o exceptie, in caz ca da, se va returna null
     * @throws InvalidCatalogException in caz ca fisierul nu exista
     * @throws IOException alte exceptii posibile
     */
    public static Catalog run(String path) throws InvalidCatalogException, IOException {
        Catalog catalog = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            catalog = objectMapper.readValue(new File(path), Catalog.class);
            if(catalog == null){
                throw new InvalidCatalogException("Nu exista catalogul dat!");
            }
        } catch (IOException e) {
            System.err.println("Eroare la deschidere fisier");
        } finally {
            return catalog;
        }
    }
}
