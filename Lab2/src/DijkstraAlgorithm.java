import java.util.PriorityQueue;
public class DijkstraAlgorithm extends Algorithm{
    private double[][] distanta=new double[100][100];
    private Location[][] predecesor=new Location[100][100];
    private int number;
    private int nrRoad;
    DijkstraAlgorithm(Problem pb){
        locations = pb.getLocations();
        roads = pb.getRoads();
        number = pb.getNumber();
        nrRoad = pb.getIntRoad();
    }

    /**
     * cream un PriorityQueue dupa care facem un for pentru a folosi algoritmul lui Dijkstra din fiecare locatie
     * cream un for pentru a initializa tot vectorul de distanta cu "infinit" di predecero cu null si le adaug in queue
     * setam sursa cu 0 dupa care o stergem si o adaugam din nou ca sa se updateze queue(o facem de fiecare data cand schimbam distanta si/sau predecesorul
     * facem un while care va rula pana nu va fi lista cu prioritati goala
     * ii asignam lui l valoare localitatii celei mai prioritare si o stergem dupa de pe lista
     * cream un for care va rula pentru fiecare drum existent
     * vom crea doua ifuri care vor vedea daca capetele drumului nu sunt egale cu localitatea 'l'
     * in caz ca da lui v ii va fi asignat valoarea celuilalt capat a drumuli si se va vedea daca acesta mai e in priority queue
     * in caz ca este, vom vedea care e distanta in caz ca vom face acest drum si in caz ca e mai miga vom updata datele.
     * vom seta vectorii cu distanta si predecesori in clasa solutie care o vom returna
     * @return solutia pentru problema data
     */
    public Solution solve(){
        PriorityQueue<Location> priority=new PriorityQueue<>();
        Location locatieTemp;
        double temp;
        int nr;
        for(int i=0;i<number;i++) {
            for (int j = 0; j < number; j++) {
                distanta[i][j] = 999999999;
                locations[j].setDistanta(distanta[i][j]);
                predecesor[i][j] = null;
                priority.add(locations[j]);
            }
            distanta[i][i]=0;
            locations[i].setDistanta(distanta[i][i]);
            priority.remove(locations[i]);
            priority.add(locations[i]);
            while(!priority.isEmpty()){
                locatieTemp=priority.peek();
                nr=locatieTemp.getNumber();
                priority.remove();
                for(int k=0;k< nrRoad;k++){
                    if(roads[k].getLegatura2().name.equals(locatieTemp.name) ) {
                        Location celalaltCapat=roads[k].getLegatura1();
                        if(priority.contains(celalaltCapat)) {
                            temp = distanta[i][nr] + roads[k].getLenght();
                            if (temp < distanta[i][celalaltCapat.getNumber()]) {
                                distanta[i][celalaltCapat.getNumber()]=temp;
                                locations[celalaltCapat.getNumber()].setDistanta(temp);
                                predecesor[i][celalaltCapat.getNumber()]=locatieTemp;
                                priority.remove(celalaltCapat);
                                priority.add(celalaltCapat);
                            }
                        }
                    }else if(roads[k].getLegatura1().name.equals(locatieTemp.name)){
                        Location celalaltCapat=roads[k].getLegatura2();
                        if(priority.contains(celalaltCapat)) {
                            temp = distanta[i][nr] + roads[k].getLenght();
                            if (temp < distanta[i][celalaltCapat.getNumber()]) {
                                distanta[i][celalaltCapat.getNumber()]=temp;
                                locations[celalaltCapat.getNumber()].setDistanta(temp);
                                predecesor[i][celalaltCapat.getNumber()]=locatieTemp;
                                priority.remove(celalaltCapat);
                                priority.add(celalaltCapat);
                            }
                        }
                    }
                }
            }
        }
        solutie.setDistanta(distanta);
        solutie.setPredecesor(predecesor);
        return solutie;
    }
}
