public class Road {
    private int speed;
    private int lenght;
    private String type;
    public Road(){
        this.type=new String();
    }

    public int getLenght() {
        return lenght;
    }

    public int getSpeed() {
        return speed;
    }

    public String getType() {
        return type;
    }

    public void setLenght(int lenght) {
        this.lenght = lenght;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString(){
        return "Drumul este de tip: " + this.type + ", are lungimea: "+ this.lenght + ", viteza maxima premisa este: "
                + this.speed;
    }
}
