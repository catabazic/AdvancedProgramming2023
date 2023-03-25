import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Faker faker = new Faker();
        Student[] students = IntStream.rangeClosed(0, 2)
                .mapToObj(i -> new Student(faker.name().fullName()))
                .toArray(Student[]::new);

        List<Student> LinkedList  = new ArrayList<>();
        LinkedList.addAll( Arrays.asList(students) );
        List<Student> newSortedList = LinkedList.stream()
                .sorted(Comparator.comparing(Student::getName))
                .collect(Collectors.toList());
        Project[] projects=IntStream.rangeClosed(0,2)
                .mapToObj(i -> new Project("P" + i))
                .toArray(Project[]::new);
        List<Project> TreeSet = new ArrayList<>();

        TreeSet.addAll(Arrays.asList(projects));
        List<Project> newTreeSet = TreeSet.stream()
                .sorted(Comparator.comparing(Project::getName))
                .collect(Collectors.toList());

        Problem problem=new Problem();
        problem.addPreference(students[0],Arrays.asList(projects[0],projects[2]));
        problem.addPreference(students[1],Arrays.asList(projects[1],projects[2]));
        problem.addPreference(students[2],Arrays.asList(projects[0]));

        Greedy rezolvare=new Greedy(problem);
        rezolvare.solvingProblem();
        System.out.println(rezolvare);
    }
}
