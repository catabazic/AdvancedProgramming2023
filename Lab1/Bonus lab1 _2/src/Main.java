import java.util.Scanner;
import java.lang.Math;

public class Main {
    /**
     * Crearea matricei a unui graf k-regulat unde k este un numar par
     * @param k gradul nodurilor
     * @param n numarl nodurilor
     * @return matricea de adicenta a grfului dat
     */
    public static int[][] evenKRegular(int k,int n){
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
        int m=k-2;//de cate muchii mai avem nevoie din fiecare nod
        if(m>0){
            for(int i=0;i<n;i++){
                for(int j=0;j<m/2;j++){//adaugam muchii catre k/2 noduri vecine de pe fiecare parte
                    array[i][(i+2+j)%n]=1;
                    if((i-2-j)%n<0) {
                        array[i][(i-2-j)%n+5]=1;
                    }else{
                        array[i][(i-2-j)%n]=1;
                    }
                }
            }
        }
        return array;
    }

    /**
     * crearea grafului k-regulat unde k este impar(si numarul de noduri este par)
     * @param k gradul nodurilor
     * @param n numaru de noduri
     * @return matricea de adiacenta a grafului respectiv
     */
    public static int[][] oddKRegular(int k, int n){
        int [][] array =new int[n][n];
        array=evenKRegular(k,n);// cream matricea pentru k-1
        for(int i=0;i<n;i++){
            array[i][(i+n/2)%n]=1;// aadauga o muchie catre nodul opus
        }
        return array;
    }
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.print("Numarul  de noduri: ");
        int vertexNumber=scanner.nextInt();
        System.out.print("Gradul nodurilor: ");
        int vertexDegree=scanner.nextInt();

        if(vertexDegree>vertexNumber || (vertexDegree%2!=0 && vertexNumber%2!=0)){
            System.out.println("Nu poate exista un astfel de graf!");
        }else{

            int[][] array=new int[vertexNumber][vertexNumber];
            if(vertexDegree%2==0){
                array=evenKRegular(vertexDegree,vertexNumber);
            }else{
                array=oddKRegular(vertexDegree,vertexNumber);
            }

            for(int i=0;i<vertexNumber;i++){
                for(int j=0;j<vertexNumber;j++){
                    System.out.print(array[i][j]+" ");
                }
                System.out.print("\n");
            }

        }
    }
}