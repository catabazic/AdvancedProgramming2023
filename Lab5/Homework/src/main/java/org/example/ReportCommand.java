package org.example;
import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ReportCommand implements Commands{
    /**
     * Vom crea un map de documente pentru a le folosi la template ul pentur formatare in HTML, in care adaugam toate documentele din clasa cat
     * Folosim clasa Configuration pentru a seta calea catre template ul care il vom folosi
     * Folosim clasa Template pentru a extrage template ul deja creat si a crea dupa acesta un file html
     * Clasa FileWriter ne ajuta sa scriem in fisier ul nou creat
     * @param cat Catalogul continutul caruia va fi stocat in un html file
     * @throws IOException
     * @throws TemplateException
     */
    public static void run(Catalog cat) throws IOException, TemplateException {
        Map<String, Object> data = new HashMap<>();
        data.put("docs", cat.getDocuments());

        Configuration c=new Configuration(Configuration.VERSION_2_3_32);
        c.setDirectoryForTemplateLoading(new File("D:\\IT\\FII 2sem2\\PA\\Lab5\\Homework\\template"));

        Template template = c.getTemplate("template.ftl");
        FileWriter writer = new FileWriter("catalog-report.html");
        template.process(data, writer);
        writer.close();
    }
}
