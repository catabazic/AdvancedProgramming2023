public class Designer extends Person {
    private DesignerEnum domeniu;

    Designer(){}

    /**
     * designerul are nume, data nasterii si domeniul exact de munca
     * @param nume
     * @param dataNastere
     * @param domeniu una din cele propuse in DesigneEnum
     */
    Designer(String nume, String dataNastere, DesignerEnum domeniu){
        this.name=nume;
        this.birthDate=dataNastere;
        this.domeniu=domeniu;
    }
    public DesignerEnum getDomeniu() {
        return domeniu;
    }

    public void setDomeniu(DesignerEnum domeniu) {
        this.domeniu = domeniu;
    }
}
