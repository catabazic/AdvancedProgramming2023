import java.util.ArrayList;

public class Solution {
    private double distanta[][];
    private Location predecesor[][];

    /**
     * folosit de dijkstra pentru a seta distanta in clasa respectiva
     * @param d vecorul de distante
     */
    public void setDistanta(double[][] d){
        distanta=d;
    }

    /**
     * folosit de dijkstra pentru a seta predecesorii in clasa respectiva
     * @param p vectorul de predecesori
     */
    public void setPredecesor(Location[][] p){
        predecesor=p;
    }

    /**
     * adaugam in un array de locatii predecesorii incepand de la locatia desinatie pana nu ajungem la locul de unde pornim
     * resultatul il vom adauga in stringul pe care il vom returna in forma inversata
     * @param pornim  locatia din care vrem sa pornim
     * @param ajungem locatia in care vrem sa ajungem
     * @return drumul urmat pentru a ajunge l destinatie si distanta intre acestea
     */
    public String toString(Location pornim, Location ajungem){
        Location locatieAnterioara;
        locatieAnterioara=predecesor[pornim.getNumber()][ajungem.getNumber()];
        String result=new String();
        ArrayList<Location> temp=new ArrayList<>();
        temp.add(ajungem);
        while (true){
            temp.add(locatieAnterioara);
            locatieAnterioara=predecesor[pornim.getNumber()][locatieAnterioara.getNumber()];
            if(locatieAnterioara==null){
                break;
            }
        }
        result="Distanta de la " + pornim.getName() + " pana la " + ajungem.getName() + " este "
                + distanta[pornim.getNumber()][ajungem.getNumber()] + ". Drumul care cel mai scurt este : - ";
        for(int i=temp.size();i>0;i--){
            result+=temp.get(i-1).getName()+ " - ";
        }
        return result;
    }
}