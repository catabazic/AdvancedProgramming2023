import java.util.Comparator;
public class City extends Location {
    private int population;

    City(){}
    City(String nume, int X, int Y, int popupatie){
        name=nume;
        coordonation[0]=X;
        coordonation[1]=Y;
        population=popupatie;
    }
    public int getPopulation() {
        return population;
    }
    public void setPopulation(int population) {
        this.population = population;
    }

    /**
     * compara doua obiecte pentru a le pune in priority queue in functie de distanta
     * @param other the object to be compared.
     */
    @Override
    public int compareTo(Location other) {
        // override compareTo method from Location
        if (other instanceof City) {
            City otherCity = (City) other;
            // compare cities based on their distance field
            return Double.compare(this.getDistanta(), otherCity.getDistanta());
        } else {
            // fallback to the compareTo method from Location
            return super.compareTo(other);
        }
    }
}
