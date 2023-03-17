import java.util.Comparator;
public class Project implements StudentProject, Comparator<Project>{
    private String name ;
    Project(String name){
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
    public int compare(Project p1, Project p2) {
        return p1.getName().compareTo(p2.getName());
    }
}
