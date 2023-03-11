public class Problem {
    private Location[] locations=new Location[100];
    private Road[] roads=new Road[100];
    private int intLocation;
    private int intRoad;

    Problem(){
        intLocation=0;
        intRoad=0;
    }

    /**
     * verifica daca numele este unic prin parcurgerea vectorilor de locatie si drumuri si verificand fiecare obiect in parte
     * in caz ca trece cu succes, ocatia va fi adaugata in vector, va fi setat un numar pentru locatie(pentru dijkstra)
     * @param l obiectul de tip Location care va fi adaugat in vectorul de Locatii
     * @return 0 in caz ca locatia are un nume unic si -1 in caz contrar
     */
    public int addLocation(Location l){
        for(int i=0;i<intLocation;i++){
            if(l.getName().equals(locations[i].getName())){
                System.out.println("Numele e deja folosit!");
                return -1;
            }
        }
        for(int i=0;i<intRoad;i++){
            if(l.getName().equals(roads[i].getName())){
                System.out.println("Numele e deja folosit!");
                return -1;
            }
        }
        locations[intLocation]=l;
        locations[intLocation].setNumber(intLocation);
        intLocation++;
        return 0;
    }

    /**
     * similar cu addLocation
     * @param r drumul care va fi adaugat in vectorul de drumuri
     * @return similar cu addLocation
     */
    public int addRoad(Road r){
        for(int i=0;i<intLocation;i++){
            if(r.getName().equals(locations[i].getName())){
                System.out.println("Numele e deja folosit!");
                return -1;
            }
        }
        for(int i=0;i<intRoad;i++){
            if(r.getName().equals(roads[i].getName())){
                System.out.println("Numele e deja folosit!");
                return -1;
            }
        }
        roads[intRoad]=r;
        intRoad++;
        return 0;
    }
    public Location[] getLocations() {
        return locations;
    }

    public Road[] getRoads() {
        return roads;
    }
    public int getNumber(){
        return intLocation;
    }

    public int getIntRoad() {
        return intRoad;
    }

    /**
     * verifica daca intre doua locatii este un drum
     * se face un for pentru fiecare drum
     * @param l1 locatia 1
     * @param l2 locatia 1
     * @return true in caz ca exista drum intre aceste doua si false in caz contrar
     */
    public boolean exists(Location l1, Location l2){
        Location temp;
        int i=0;
        for(int j=0;j<intRoad;j++) {
            i=j;
            if (roads[i].getLegatura1().getName().equals(l1.getName())) {
                temp = roads[i].getLegatura2();
                if (temp.getName().equals(l2.getName())) {
                    return true;
                }
            }else if(roads[i].getLegatura2().getName().equals(l1.getName())){
                temp = roads[i].getLegatura1();
                if (temp.getName().equals(l2.getName())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * se fac doua vectore in care se adauga drumurile si locatiile dupa care acestea sunt returnate
     * @return informatiile despre obiectul respectiv
     */
    @Override
    public String toString(){
        String drum=new String();
        for(int i=0;i<intRoad;i++){
            drum+=roads[i].getName()+ ", ";
        }
        String locatii=new String();
        for(int i=0;i<intLocation;i++){
            locatii+=locations[i].getName()+ ", ";
        }
        return "Sunt prezente urmatoarele drumuri: " + drum + "De asemenea, avem urmatoarele localitati: " + locatii;
    }
}
