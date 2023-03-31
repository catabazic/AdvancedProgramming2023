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

    /**
     * cream un string care va retine toate informatiile despre clasa data
     * @return toate informatiile despre clasa
     */
    @Override
    public String toString(){
        return "{ ID :  " + id + "; Titlul : " + title + "; Location : " + location+ " } ";
    }
}
