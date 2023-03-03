import java.util.Scanner;
//pentru rulare am folosit:
//javac -d bin src/Main.java
//java -cp bin Main 30
public class Main {

    /**
     * Functia pentru a crea matricea in care pe fiecare linie si coloana numerele vor fi unice
     * @param n este diminsiunea maricei
     * @return matricea creata
     */
    private static int[][] latinSquer(int n){
        int[][] array = new int[n][n];
        for(int i=1; i<=n; i++){//umplem primul rand al matricei
            array[0][i-1]=i;
        }

        for(int i=1; i<n; i++){
            int k=i+1;//pe fiecare rand vom incepe cu un numar mai mare
            for(int j=0; j<n; j++){
                array[i][j]= k;
                if(k==n){
                    k=1;//in caz ca am afisat deja numarul n  vom continua cu 1
                }else{
                    k++;
                }
            }
        }
        return array;
    }
    public static void main(String[] args) {
        if (args.length > 1) {
            System.out.println("Too many arguments!");
            System.exit(-1);
        }
        long t1 = System.currentTimeMillis();//pentru a afisa timpul
        int n =Integer.parseInt(args[0]);

        int[][] array = latinSquer(n);

        if(n<100) {
            for (int i = 0; i < n; i++) {
                String matrix = new String();//pentru fiecare rand cream un string care va retine toate valorile de pe randul respectiv si il vom afisa
                for (int j = 0; j < n; j++) {
                    matrix += array[i][j] + "  ";
                }
                System.out.println(matrix);
            }
        }else{
            long t2 = System.currentTimeMillis();
            long time=t2-t1;//timpul de rulare a programului
            System.out.println("time: " + time + "ms");
        }

    }
}