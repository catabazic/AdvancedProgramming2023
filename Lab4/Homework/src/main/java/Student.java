import java.util.Comparator;
public class Student implements StudentProject, Comparator<Student> {
    private String name;
    Student(String name){
        this.name=name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return this.name;
    }
    @Override
    public String toString(){
        return this.name;
    }
    @Override
    public int compare(Student s1, Student s2){
        return s1.getName().compareTo(s2.getName());
    }
}
