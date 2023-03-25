import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Greedy {
    private Problem problem=new Problem();
    private Map<Student,Project> result =new HashMap<>();
    Greedy(Problem prob){
        problem=prob;
    }

    /**
     * intr o bucla vom verifica initial daca proiectul din lista de preferinte de pe pozitia i este deja luat sau nu
     * in caz ca proectul de pe pozitia i inca nu este luat vom adauga in map-ul result pentru studentul s proiectul d, vom adauga proiectul i lista cu proiecte luate si vom returna 0
     * in caz ca proiectul e deja luat vom incrementa i. In caz ca i este egal cu marimea listei de preferinte a studentului, atunci nu are cum sa ia si el un proiect si returnam -1
     * @param taken lista de proiecte care sunt deja luate
     * @param s studentul pentru care urmeaza sa alegem un proiect
     * @return 0 in caz ca exista un proect din lista de preferinte care inca nu e ales de altcineva si -1 in caz contrar
     */
    public int addStudent(List<Project> taken, Student s){
        int i=0;
        while(true) {
            if (!taken.contains(problem.prefMap.get(s).get(i))) {
                result.put(s, problem.prefMap.get(s).get(i));
                taken.add(problem.prefMap.get(s).get(i));
                return 0;
            }else{
                i++;
                if(i==problem.prefMap.get(s).size()){
                    return -1;
                }
            }
        }
    }

    /**
     * distribuie fiecarui student un proiect in functie de preferintele acestuia
     * cream o variabila booleana pentru return, o lista de proiecte care sunt luate si o lista de studenti care au numarul dee preferinte sub medie
     * pentru studentii cu preferinte sub medie vom folosi functia addStudent si in caz ca e -1 inseamna ca nu a fost posibila distribuirea unui proiect pentrur acesta
     * pentru toti stundetii din map-ul de studenti vom alege studentii care au preferintele peste medie si vom proceda la fel ca si in primul caz
     * @return true in caz ca e posibila impartirea proiectelor studentilor in functie de preferinte si false in caz contrar
     */
    public boolean solvingProblem(){
        boolean esteRezolvabil=true;
        List<Student> studs=problem.query();
        List<Project> taken=new ArrayList<>();
        for(Student s: studs){
            int isOk=addStudent(taken,s);
            if(isOk==-1){
                esteRezolvabil=false;
            }
        }
        for(Student s: problem.prefMap.keySet()){
            if(!result.containsKey(s)){
                int isOk=addStudent(taken,s);
                if(isOk==-1){
                    esteRezolvabil=false;
                }
            }
        }
        return esteRezolvabil;
    }
    @Override
    public String toString(){
        return result.toString();
    }
}
