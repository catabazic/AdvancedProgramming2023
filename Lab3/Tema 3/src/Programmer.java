public class Programmer extends Person{
    /**
     * fiecare programator are un nume, o data de nastere si un nivel la care se afla
     * @param nume numele dat
     * @param dataNastere data nasterii
     * @param level un nivel ales din ProgrammerEnum
     */
    Programmer(String nume, String dataNastere, ProgrammerEnum level){
        this.name=nume;
        this.birthDate=dataNastere;
        this.level=level;
    }
    Programmer(){}
    private ProgrammerEnum level;

    public ProgrammerEnum getLevel() {
        return level;
    }

    public void setLevel(ProgrammerEnum level) {
        this.level = level;
    }
}
