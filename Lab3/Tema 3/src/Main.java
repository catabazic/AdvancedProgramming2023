import java.util.ArrayList;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        /* pentru partea de compulsory
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
        }*/
        Person persoana1=new Designer("Andrei","12.12.2000",DesignerEnum.LOGO);
        Person persoana2= new Designer("Mcarcel","30.04.1997",DesignerEnum.SITE);
        Person persoana3=new Programmer("Alisa","26.01.1993",ProgrammerEnum.SENIOR);
        Person persoana4=new Programmer("Ion","04.08.1997",ProgrammerEnum.MID_LEVEL);
        Person persoana5=new Programmer("Simona","12.12.2001",ProgrammerEnum.JUNIOR);

        Company RomSoft=new Company("RomSoft","01.03.2009");
        Company Mambu=new Company("Mambu","12.06.2010");

        persoana1.addRelationship(RomSoft,"UI Designer");
        persoana1.addRelationship(persoana3,"colegi");
        persoana2.addRelationship(Mambu,"Front-end Deveoper");
        persoana3.addRelationship(RomSoft,"Back-end Developer");
        persoana4.addRelationship(Mambu,"QA");
        persoana5.addRelationship(Mambu,"Project Manager");

        Network network=new Network();
        network.addNode(persoana2);
        network.addNode(persoana1);
        network.addNode(persoana4);
        network.addNode(persoana3);
        network.addNode(persoana5);
        network.addNode(RomSoft);
        network.addNode(Mambu);

        System.out.println(network);
    }
}