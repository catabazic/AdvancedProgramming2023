import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Main {

    public static void main() {
        Student[] students = IntStream.rangeClosed(0, 2)
                .mapToObj(i -> new Student("S" + i))
                .toArray(Student[]::new);

        List<Student> LinkedList  = new ArrayList<>();
        LinkedList.addAll( Arrays.asList(students) );
        List<Student> newSortedList = LinkedList.stream()
                .sorted(Comparator.comparing(Student::getName))
                .collect(Collectors.toList());
        for(Student s : newSortedList){
            System.out.println(s);
        }

        Project[] projects=IntStream.rangeClosed(0,2)
                .mapToObj(i -> new Project("P" + i))
                .toArray(Project[]::new);
        List<Project> TreeSet = new ArrayList<>();
        TreeSet.addAll(Arrays.asList(projects));
        List<Project> newTreeSet = TreeSet.stream()
                .sorted(Comparator.comparing(Project::getName))
                .collect(Collectors.toList());
        for(Project p: newTreeSet){
            System.out.println(p);
        }
    }
}
