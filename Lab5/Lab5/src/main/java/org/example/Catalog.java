package org.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Catalog implements Serializable {
    private String name;
    private List<Document> documents = new ArrayList<>();

    Catalog(String n){
        name=n;
    }

    public void add(Document doc) {
        documents.add(doc);
    }
    public Document findById(String id) {
        return documents.stream()
         .filter(d -> d.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public String toString(){
        String result="\"Name\":"+ name+"\" \\n \"Documents\":{ \\n";
        for(int i=0;i<documents.size();i++){
            result+=documents.get(i);
        }
        result+="}";
        return result;
    }
}
