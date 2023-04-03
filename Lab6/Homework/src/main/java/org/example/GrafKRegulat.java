package org.example;

/**
 * Clasa este folosita de la bonusului de la laboratorul 1(cu mici modificari)
 */
public class GrafKRegulat {
    private static int vertexD;
    private static int[][] evenKRegular(int k,int n){
        int [][] array =new int[n][n];
        for(int i=0;i<n;i++){
            if(i==0){
                array[i][1]=1;
                array[i][n-1]=1;
            }else if(i==n-1){
                array[i][0]=1;
                array[i][i-1]=1;
            }else{
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
                        array[i][(i-1-j)%n+n-1]=1;
                    }else{
                        array[i][(i-2-j)%n]=1;
                    }
                }
            }
        }
        return array;
    }
    private static int[][] oddKRegular(int k, int n){
        int [][] array =new int[n][n];
        array=evenKRegular(k,n);// cream matricea pentru k-1
        for(int i=0;i<n;i++){
            array[i][(i+n/2)%n]=1;// aadauga o muchie catre nodul opus
        }
        return array;
    }

    /**
     * Ca sa fie un joc valid, am adaugat faprul ca daca gradul nodurilor e mai mic de 4 va lua valoarea 4(nu putem crea triungi daca e mai mic)
     * in caz ca nu e posibil sa existe graful cu numarul de noduri si muchii coresponzator vom incrementa gradul nodurilor
     * @param vertexDegree
     * @param vertexNumber
     * @return
     */
    public static int[][] getMatrix(int vertexDegree,int vertexNumber) {
        vertexD=vertexDegree;
        if(vertexD<4){
            vertexD=4;
        }
        if((vertexD%2!=0 && vertexNumber%2!=0)){
            vertexD++;
        }
        int[][] array=new int[vertexNumber][vertexNumber];
        if(vertexD%2==0){
            array=evenKRegular(vertexD,vertexNumber);
        }else{
            array=oddKRegular(vertexD,vertexNumber);
        }
        return array;
    }

    /**
     * Am mai creat o functie pentru a determina matricea de incidenta a jocului.
     * Vom crea matricea de adacenta a grafului
     * Prin folosirea a 2 for-uri vom modifica  matricea de incidenta in caz ca pe posizia i,j din matricea de adiacenta este egal cu 1, vom asigna la positia i/j,numarul muchiei valoarea 1
     * @param vertexDegree gradul tuturor nodurilor
     * @param vertexNumber numarul de noduri
     * @return matricea de incidenta
     */
    public static int[][] incidenceFromAdiacence(int vertexDegree,int vertexNumber){
        int[][] adiacence=getMatrix(vertexDegree,vertexNumber);
        int colums=((adiacence.length)*vertexD/2);
        int[][] incidence=new int[adiacence.length][colums];
        int edges=0;
        for(int i=0; i< adiacence.length; i++){
            for(int j=i+1;j< adiacence.length;j++){
                if(adiacence[i][j]==1){
                    incidence[i][edges]=1;
                    incidence[j][edges]=1;
                    edges++;
                }
            }
        }
        return incidence;
    }
}
