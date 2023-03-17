import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Network {
    private ArrayList<ArrayList<Integer>> matriceAdiacenta;
    private List<Node> nodes = new ArrayList<>();

    public void addNode(Node node) {
        nodes.add(node);
        matriceAdiacenta.add(matriceAdiacenta.size(), new ArrayList<>());
    }

    public void Relationship(Person node){
        Map<Node, String> relationships=node.getRelationships();
        for(int i=0;i<nodes.size();i++){
            if(relationships.containsKey(nodes.get(i))) {
                matriceAdiacenta.get(nodes.indexOf(node)).add(i);
            }
        }
    }
    public void removeNode(int i){
        nodes.remove(nodes.get(i));
        matriceAdiacenta.remove(i);
    }

    public List<Node> getNodes() {
        return nodes;
    }

    /**
     * vedem daca exista sau nu nodul resprectiv prin iterarea vectorlui
     * @param other nodul propus
     * @return true in caz ca exista si false in cas contrar
     */

    public boolean isNode(Node other){
        for(int i=0;i<nodes.size();i++){
            if(nodes.equals(other)){
                return true;
            }
        }
        return false;
    }

    /**
     * pentru fiecare nod cauutam cate telatii are si retirnam numarul relatiilor
     * @param other nodul cerut
     * @return numarul de relaatii
     */
    public int importance(Node other){
        if(isNode(other) && (other instanceof Person)){
            return ((Person) other).getRelationships().size();
        }
        return -1;
    }

    @Override
    public String toString(){
        String persoane="Avem urmatoarele persoane: \n";
        for(int i=0; i<nodes.size();i++){
            if(nodes.get(i) instanceof Person) {
                persoane += nodes.get(i) + "\n";
            }
        }
        String companii="Avem urmatoarele companii: \n";
        for(int i=0;i<nodes.size();i++){
            if(nodes.get(i) instanceof Company){
                companii+= nodes.get(i)+"\n";
            }
        }
        return "Network contine: \n" + persoane+companii;
    }
}
