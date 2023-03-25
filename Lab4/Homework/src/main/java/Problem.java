import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Problem {
    Map<Student, List<Project>> prefMap = new HashMap<>();
    public void addPreference(Student s, List<Project> p){
        prefMap.put(s, p);
    }

    /**
     * vom calcula media preferintelor prin adaugarea lungimilor preferintelor al fiecarui student la sum supa care sum il vom imprati la numarul de studenti din map
     * cu ajutorul stream-ului vom filtra lista studentilor adaugand in lista doar cei care au numarul preferintelor sub medie.
     * @return lista studentilor cu numarul preferintelor sub medie
     */
    public List<Student> query(){
        int sum=0;
        for(Student s: prefMap.keySet()){
            sum+=prefMap.get(s).size();
        }
        final int AVG=sum/prefMap.size();
        List<Student> result= prefMap.keySet().stream()
                .filter(s -> prefMap.get(s).size()<=AVG)
                .collect(Collectors.toList());
        return result;
    }
}
