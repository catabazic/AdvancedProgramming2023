public class Location {
    private String name;
    private String type;
    private int [] coordonation;
    public Location(){
        this.coordonation =new int[2];
        this.name=new String();
        this.type=new String();
    }


    public void setName(String n){
        this.name=n;
    }
    public void setType(String t){
        this.type=t;
    }
    public void setCoordonation(int x, int y){
        this.coordonation[0]=x;
        this.coordonation[1]=y;
    }
    public String getName(){
        return this.name;
    }
    public String getType(){
        return this.type;
    }
    public int[] getCoordonation(){
        return this.coordonation;
    }

    @Override
    public String toString(){
        return "Locatia are numele: " +  this.name + ", tipul ei este: " + type + ", se afla la coordonatele: [ " +
                coordonation[0] + " , " + coordonation[1] + " ]";
    }
}
