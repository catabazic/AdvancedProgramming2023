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

    public List<Document> getDocuments() {
        return documents;
    }

    /**
     * vom crea un string cu toate variabilele clasei: numele si stringul cu documente pe care le are
     * @return datele despre clasa in format de string
     */
    @Override
    public String toString(){
        String result="{ Name : "+ name+"; Documents : [ ";
        for(int i=0;i<documents.size();i++){
            result+=documents.get(i);
        }
        result+="] }";
        return result;
    }
}
