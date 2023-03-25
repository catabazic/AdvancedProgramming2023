package org.example;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Document implements Serializable {
    private String id;
    private String title;
    private String location; //file name or Web page

    Document(String t, String i, String l){
        id=i;
        title=t;
        location=l;
    }

    public String getId() {
        return id;
    }

    private Map<String, Object> tags = new HashMap<>();
    public void addTag(String key, Object obj) {
        tags.put(key, obj);
    }

    @Override
    public String toString(){
        return "{ \"ID\" : \" " + id + "\" \\n \"Titlul\":\"" + title + "\" \\n \"Location\":\"" + location+ "\"} \\n";
    }
}
