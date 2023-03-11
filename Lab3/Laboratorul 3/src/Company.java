public class Company implements Node, Comparable<Company>{
    private String name;
    @Override
    public String getName(){
        return this.name;
    }
    public void setName(String n){
        this.name=n;
    }
    @Override
    public int compareTo(Company other){
        return this.name.compareTo(other.name);
    }
}
