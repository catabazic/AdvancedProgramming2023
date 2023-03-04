import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Location NewYork=new Location();
        NewYork.setName("New York");
        NewYork.setType("City");
        NewYork.setCoordonation(53,74);

        Location California=new Location();
        California.setName("California");
        California.setType("City");
        California.setCoordonation(73,40);

        Road ER1=new Road();
        ER1.setType("National");
        ER1.setLenght(100);
        ER1.setSpeed(60);

        System.out.println(NewYork.toString());
        System.out.println(California.toString());
        System.out.println(ER1.toString());
    }
}