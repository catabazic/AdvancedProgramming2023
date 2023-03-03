import java.util.Scanner;

public class Main {
    /**
     * Functia care va crea matricea de adicenta a unui graf circuit de orice marime
     * @param n numarul de noduri al grafului
     * @return matricea de adiacenta a grafului respectiv
     */
    private static int [][] matriceAdiacenta(int n){
        int [][] array =new int[n][n];
        /*umplem matricea de adiacenta astfel incat sa fie un circuit*/
        for(int i=0;i<n;i++){
            if(i==0){
                array[i][1]=1;
                array[i][n-1]=1;
            }else if(i==n-1){
                array[i][0]=1;
                array[i][i-1]=1;
            }else{//in afara de primul si ultimul nod fiecare nod este vecin cu nodul 'mai mic' si 'mai mare'
                array[i][i-1]=1;
                array[i][i+1]=1;
            }
        }
        return array;
    }

    /**
     * Functia folosita pentru a ridica matricea obtinuta la pasul anterior la puterea n
     * @param array matricea de adicenta a grafului
     * @param n nr de noduri/ puterea la care se va ridica
     * @return matricea initiala la puterea n
     */
    private static int[][] matriceLaPutere(int[][] array, int n){
        //avem nevoie de un depozitoriu pentru matricea intiala
        int [][] arrayTemp=new int [n][n];
        for (int a = 0; a < n; a++) {
            for (int b = 0; b < n; b++) {
                arrayTemp[a][b]=array[a][b];
            }
        }

        //vom stoca noua matrice la urmatoarea putere
        int [][] temp=new int [n][n];

        for(int i=1; i<n; i++){//interam de n-1 ori pentru a ajungem la puterea n
            for(int j=0; j<n; j++){ //liniile
                for(int k=0; k<n; k++){ //coloanele
                    for(int m=0; m<n; m++){ //inmultim matricea la ultima putere cu matricea initiala
                        temp[j][k]+=array[j][m]*arrayTemp[m][k];
                    }
                }
            }
            for (int a = 0; a < n; a++) {//actualizam matricea cu matricea la o putere noua
                for (int b = 0; b < n; b++) {
                    array[a][b]=temp[a][b];
                    temp[a][b]=0;
                }
            }
            /*
            for(int d=0; d<n; d++){
                for(int a=0; a<n; a++){
                    System.out.print(array[d][a] + "  ");
                }
                System.out.println();
            }
            System.out.println();*/
        }
        return array;
    }
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();

        int [][] array =matriceAdiacenta(n);
        array=matriceLaPutere(array,n);

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                System.out.print(array[i][j] + "  ");
            }
            System.out.println();
        }
    }
}