import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        /*Location NewYork=new Location(); //FOLOSIT LA COMPULSORY
        NewYork.setName("New York");
        //NewYork.setType(LocationEnum.CITY);
        NewYork.setCoordonation(53,74);

        Location California=new Location();
        California.setName("California");
        //California.setType(LocationEnum.CITY);
        California.setCoordonation(73,40);

        Road ER1=new Road();
        ER1.setType(RoadEnum.NATIONAL);
        ER1.setLenght(100);
        ER1.setSpeed(60);

        System.out.println(NewYork.toString());
        System.out.println(California.toString());
        System.out.println(ER1.toString());*/
        Problem pb = new Problem();
        Location California= new City("California", 54,78,2_345_789);
        Location NewYork= new City("NewYork",78,30,4_238_483);
        Location Rompetrol=new GasStation("Rompetrol",54,65,6.7,7.3);
        Location BlueAir=new Airport("BlueAir",12,67,6);
        Location Lucoil=new GasStation("Lucoil",23,98,6.6,7.5);
        Road R12=new Road("R12",40,20,RoadEnum.LOCAL,California,NewYork);
        Road B76=new Road("B76",60,50,RoadEnum.EXPRESS,NewYork,BlueAir);
        Road A43=new Road("A43",40,50,RoadEnum.LOCAL,NewYork,Rompetrol);
        Road A56=new Road("A56",40,30,RoadEnum.LOCAL,California,BlueAir);

        pb.addLocation(California);
        pb.addLocation(NewYork);
        pb.addLocation(Rompetrol);
        pb.addLocation(BlueAir);
        pb.addLocation(Lucoil);
        pb.addRoad(R12);
        pb.addRoad(B76);
        pb.addRoad(A56);
        pb.addRoad(A43);

        if(pb.exists(BlueAir,NewYork)) {
            System.out.println("da");
        }
        System.out.println(pb);

        Algorithm alg = new DijkstraAlgorithm(pb);
        Solution sol = alg.solve();
        System.out.println(sol.toString(Rompetrol,BlueAir));
    }
}