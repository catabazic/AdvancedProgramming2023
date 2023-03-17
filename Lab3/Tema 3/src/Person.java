import java.util.HashMap;
import java.util.Map;

public abstract class Person implements Node, Comparable<Person> {
    private Map<Node, String> relationships = new HashMap<>();
    protected String name;
    protected String birthDate;
    Person(){
        name=new String();
        birthDate=new String();
    }
    public Map<Node, String> getRelationships() {
        return relationships;
    }

    /**
     * adaugam o relatie dintre nodul respectiv si  nodul cerut
     * @param node nodul cu care vom stabili o relatie
     * @param value tipul de relatie
     */
    public void addRelationship(Node node, String value) {
        relationships.put(node, value);
        if(node instanceof Person){
            ((Person) node).relationships.put(this,value);
        }
    }
    @Override
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public int compareTo(Person other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString(){
        return "Numele este : " + this.name + ", data nasterii este : " + this.birthDate;
    }
}
