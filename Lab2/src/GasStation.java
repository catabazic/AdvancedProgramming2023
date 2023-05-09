import java.util.Comparator;
public class GasStation extends Location{
    private double diselPrice;

    private double gasPrice;
    GasStation(){}
    GasStation(String nume, int X, int Y, double disel, double benzina){
        name = nume;
        coordonation[0] = X;
        coordonation[1] = Y;
        diselPrice = disel;
        gasPrice = benzina;
    }
    public double getDiselPrice() {
        return diselPrice;
    }

    public void setDiselPrice(int diselPrice) {
        this.diselPrice = diselPrice;
    }

    public double getGasPrice() {
        return gasPrice;
    }

    public void setGasPrice(int petrolPrice) {
        this.gasPrice = petrolPrice;
    }


    /**
     * compara doua obiecte pentru a le pune in priority queue in functie de distanta
     * @param other the object to be compared.
     */
    @Override
    public int compareTo(Location other) {
        // override compareTo method from Location
        if (other instanceof GasStation) {
            GasStation otherGasStation = (GasStation) other;
            // compare cities based on their distance field
            return Double.compare(this.getDistanta(), otherGasStation.getDistanta());
        } else {
            // fallback to the compareTo method from Location
            return super.compareTo(other);
        }
    }
}
