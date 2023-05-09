import java.util.Comparator;
abstract public class Location implements Comparable<Location>{
    protected String name;
    //protected LocationEnum type; //FOLOSIT LA COMPULSORY
    protected int number;
    protected double distanta;
    protected int [] coordonation=new int[2];

    /*public Location(){ //FOLOSIT LA COMPULSORY
        this.coordonation =new int[2];
        this.name=new String();
    }*/

    public double getDistanta() {
        return distanta;
    }

    public void setDistanta(double distanta) {
        this.distanta = distanta;
    }
    public void deleteDistanta(){
        distanta=-1;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public  void setName(String n){
        this.name=n;
    }
    /*public void setType(LocationEnum t){ //FOLOSIT LA COMPULSORY
        this.type=t;
    }*/
    public void setCoordonation(int x, int y){
        this.coordonation[0]=x;
        this.coordonation[1]=y;
    }
    public String getName(){
        return name;
    }
    /*public LocationEnum getType(){ //FOLOSIT LA COMPULSORY
        return this.type;
    }*/
    public int[] getCoordonation(){
        return this.coordonation;
    }
    public int getCoordonationX(){
        return this.coordonation[0];
    }
    public int getCoordonationY(){
        return this.coordonation[1];
    }

    @Override
    public String toString(){
        return "Locatia are numele: " +  this.name + ", tipul ei este: " + /*type +*/ ", se afla la coordonatele: [ " +
                coordonation[0] + " , " + coordonation[1] + " ]";
    }

    /**
     * verifica daca doua locatii au acelasi nume
     * in caz ca obiectul este nul sau nu e de tip locatie atunci se va returna fals, in caz constrar, verificam daca numele e celasi.
     * @param obj obiectul cu caer va fi comparat
     * @return true in caz ca obiectele au acelasi nume sau false in caz contrar
     */
    @Override
    public boolean equals(Object obj){
        if(obj==null || !(obj instanceof Location)){
            return false;
        }
        Location other=(Location) obj;
        return name.equals(other.name);
    }

    @Override
    public int compareTo(Location other) {
        return Double.compare(this.distanta, other.distanta);
    }

}
