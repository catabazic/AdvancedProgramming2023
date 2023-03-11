import java.util.Comparator;
public class Airport extends Location{
    private int terminals;
    Airport(){}
    Airport(String nume, int X, int Y, int terminali){
        name=nume;
        coordonation[0]=X;
        coordonation[1]=Y;
        terminals=terminali;
    }
    public int getTerminals() {
        return terminals;
    }

    public void setTerminals(int terminals) {
        this.terminals = terminals;
    }

    /**
     * compara doua obiecte pentru a le pune in priority queue in functie de distanta
     * @param other the object to be compared.
     */
    @Override
    public int compareTo(Location other) {
        // override compareTo method from Location
        if (other instanceof Airport) {
            Airport otherAirport = (Airport) other;
            // compare cities based on their distance field
            return Double.compare(this.getDistanta(), otherAirport.getDistanta());
        } else {
            // fallback to the compareTo method from Location
            return super.compareTo(other);
        }
    }

}
