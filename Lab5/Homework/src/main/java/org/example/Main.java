package org.example;
import freemarker.template.TemplateException;

import java.io.IOException;

public class Main {
    public static void main(String args[]) throws IOException, InvalidCatalogException, TemplateException {
        Main app = new Main();
        app.testCreateSave();
        app.testLoadView();
    }

    private void testCreateSave() throws IOException, InvalidCatalogException, TemplateException {
        Catalog catalog =
                new Catalog("MyDocuments");
        var book = new Document("article1","D12","Piatra Neamt");
        var article = new Document("book1","A32","Suceava" );
        AddCommand.run(catalog,book);
        AddCommand.run(catalog,article);
        SaveCommand.run(catalog, "d:/IT/FII 2sem2/PA/Lab5/Homework/catalog.json");
        ReportCommand.run(catalog);
    }

    private void testLoadView() throws InvalidCatalogException, IOException {
        Catalog catalog = LoadCommand.run("d:/IT/FII 2sem2/PA/Lab5/Lab5/catalog.json");
        ListCommand.run(catalog);
        ViewCommand.run("D:\\Catalina\\smth\\logo_teahouse.PNG");
    }

}