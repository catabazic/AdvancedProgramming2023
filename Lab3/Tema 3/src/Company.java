public class Company implements Node, Comparable<Company>{
    private String name = new String();
    private String dateOpen = new String();
    Company(){}

    /**
     * fiecare companie are un nume si o data la care a fost deschida
     * @param nume numele respectiv
     * @param dateOpen data respectiva
     */
    Company(String nume, String dateOpen){
         this.name=nume;
         this.dateOpen=dateOpen;
    }
    public String getDateOpen() {
        return dateOpen;
    }

    public void setDateOpen(String dateOpen) {
        this.dateOpen = dateOpen;
    }

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
    @Override
    public String toString(){
        return "Numele este : " + this.name + ", data deschiderii este : " + this.dateOpen;
    }

}
