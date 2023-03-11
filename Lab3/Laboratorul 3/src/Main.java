import java.util.ArrayList;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        Person persoana1=new Person();
        Person persoana2= new Person();
        Company companie = new Company();

        persoana1.setName("Ion");
        persoana2.setName("Valeria");
        companie.setName("Unic Expiries");

        List<Node> nodes= new ArrayList<>();
        nodes.add(persoana1);
        nodes.add(persoana2);
        nodes.add(companie);

        for(Node node : nodes){
            System.out.println(node.getName());
        }
    }
}