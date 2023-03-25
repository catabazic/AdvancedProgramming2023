package org.example;
import java.io.IOException;

public class Main {
    public static void main(String args[]) throws IOException, InvalidCatalogException {
        Main app = new Main();
        app.testCreateSave();
        //app.testLoadView();
    }

    private void testCreateSave() throws IOException, InvalidCatalogException {
        Catalog catalog =
                new Catalog("MyDocuments");
        var book = new Document("article1","D12","Piatra Neamt");
        var article = new Document("book1","A32","Suceava" );
        catalog.add(book);
        catalog.add(article);
        CatalogUtil.save(catalog, "d:/IT/FII 2sem2/PA/Lab5/Lab5/catalog.json");
    }

    /*private void testLoadView() throws InvalidCatalogException {
        Catalog catalog = CatalogUtil.load("d:/IT/FII 2sem2/PA/Lab5/Lab5/catalog.json");
        CatalogUtil.view(catalog.findById("article1"));
    }*/

}