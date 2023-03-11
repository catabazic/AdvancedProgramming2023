import java.lang.Math;
public class Road {
    private String name=new String();
    private int speed;
    private int lenght;
    private RoadEnum type;
    private Location[] legatura= new Location[2];
    public Road(String nume, int viteza, int lungime, RoadEnum tip, Location pornire , Location destinatie){
        name=nume;
        speed=viteza;
        lenght=lungime;
        type=tip;
        legatura[0]=pornire;
        legatura[1]=destinatie;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location[] getLegatura() {
        return legatura;
    }

    public void setLegatura(Location locatie1, Location locatie2) {
        this.legatura[0]=locatie1;
        this.legatura[1]=locatie2;
    }

    public Location getLegatura1(){ return this.legatura[0];}
    public Location getLegatura2(){return this.legatura[1];}
    public int getLenght() {
        return lenght;
    }

    public int getSpeed() {
        return speed;
    }

    public RoadEnum getType() {
        return type;
    }

    /**
     * folosim formula lungimii a doua puncte pentru a ne asigura ca lungimea pe care dorim sa o setam nu va fi prea mica
     * @param lenght
     */
    public void setLenght(int lenght) {
        Location l1=legatura[0];
        Location l2=legatura[1];
        double euclidianFormula=Math.sqrt(Math.pow(l2.getCoordonationX()-l1.getCoordonationY(),2)+Math.pow(l2.getCoordonationX()-l1.getCoordonationX(),2));
        if(euclidianFormula>lenght) {
            System.out.println("Lungimea e prea mica!");
        }else {
            this.lenght = lenght;
        }
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setType(RoadEnum type) {
        this.type = type;
    }

    @Override
    public String toString(){
        return "Drumul este de tip: " + this.type + ", are lungimea: "+ this.lenght + ", viteza maxima premisa este: "
                + this.speed;
    }

    /**
     * verifica daca doua drumuri au acelasi nume
     * in caz ca obiectul este nul sau nu e de tip locatie atunci se va returna fals, in caz constrar, verificam daca numele e celasi.
     * @param obj obiectul cu caer va fi comparat
     * @return true in caz ca obiectele au acelasi nume sau false in caz contrar
     */
    @Override
    public boolean equals(Object obj){
        if(obj==null || (obj instanceof  Road)){
            return false;
        }
        Road other = (Road) obj;
        return name.equals(other.name);
    }
}
