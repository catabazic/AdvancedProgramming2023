package org.example;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.*;
import java.util.List;

public class Logic implements Serializable {
    Game game;
    Data data;

    public Logic(Game g){
        game=g;
        data=new Data();
        init();
        canvas();
    }

    /**
     * Vom folosi variabolele din clasa data cu valorile coresponzatoare a jocului, pentru a le folosi in continuare.
     * (Intelesul fiecarei variabile este explicat in clasa Data)
     */
    private void init(){
        data.turn=2;
        data.isItFinished = 0;
        data.numVertices = game.frame.configPasnel.dotsNr();
        data.probVertices = game.frame.configPasnel.LinesNr();
        data.board = GrafKRegulat.getMatrix((int)(data.probVertices*data.numVertices)-1,data.numVertices);
        data.matrix = GrafKRegulat.incidenceFromAdiacence((int)(data.probVertices*data.numVertices)-1,data.numVertices);
        data.x = game.frame.canvas.getXLines();
        data.y = game.frame.canvas.getYLines();
       /* for(int i=0;i<data.numVertices;i++){
            for(int j=0;j<data.numVertices;j++){
                System.out.print(data.board[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("\n");
        for(int i=0;i<data.numVertices;i++){
            for(int j=0;j<data.matrix[0].length;j++){
                System.out.print(data.matrix[i][j]+" ");
            }
            System.out.println();
        }*/
    }

    /**
     * Ne vom folosi de MouseListener pentru a detecta daca a fost sau nu apasat pe ecranul din canvas.
     * Daca jocul nu e finisat atunci vom verifica daca a fost apasat pe o linie din graf.
     * Folosind doua for-uri parcurgem toata matricea de adiacenta si in caz ca exista o muchie intre acestea verificam daca a fost apasat pe muchia respectiva (folosind o formula de pe fundul internetului)
     * In caz ca a fost apasat pe o muchie, vom parcurge fiecre coloana a matricei de incidenta si in caz ca data.matrix[i][n] == 1 && data.matrix[j][n] == 1 (adica nu este deja colorata) vom desena o linie de culoarea respectiva si vom updata matricea de incidenta.
     * De asemenea, verificam daca jocul nu e finalizat in urma schimbarii.
     */
    public void canvas(){
        game.frame.canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
            int getX= e.getX();
            int getY = e.getY();
            int ok=0;
            if(data.isItFinished==0) {
                for (int i = 0; i < data.numVertices; i++) {
                    for (int j = 0; j < data.numVertices; j++) {
                        if (data.board[i][j] == 1) {
                            int dist = (int) Math.sqrt((getX - data.x[i]) * (getX - data.x[i]) + (getY - data.y[i]) * (getY - data.y[i])) + (int) Math.sqrt((getX - data.x[j]) * (getX - data.x[j]) + (getY - data.y[j]) * (getY - data.y[j]));
                            if (dist == (int) Math.sqrt((data.x[i] - data.x[j]) * (data.x[i] - data.x[j]) + (data.y[i] - data.y[j]) * (data.y[i] - data.y[j]))) {
                                /*System.out.println("Am apasat pe linie!");*/
                                for (int n = 0; n < data.matrix[0].length; n++) {
                                    if (data.matrix[i][n] == 1 && data.matrix[j][n] == 1) {
                                        data.matrix[i][n] = data.turn;
                                        data.matrix[j][n] = data.turn;
                                        if (data.turn == 2) {
                                            game.frame.canvas.graphics.setColor(Color.RED);
                                        } else if (data.turn == 3) {
                                            game.frame.canvas.graphics.setColor(Color.BLUE);
                                        }
                                        game.frame.canvas.graphics.drawLine(data.x[i], data.y[i], data.x[j], data.y[j]);
                                        game.frame.canvas.repaint();
                                        if (data.turn == 2) {
                                            if (isFinish()) {
                                                game.frame.canvas.jocTerminat(data.turn);
                                                System.out.println("Jucatorul rosu a castigat!");
                                            }
                                            data.turn = 3;
                                        } else {
                                            if (isFinish()) {
                                                game.frame.canvas.jocTerminat(data.turn);
                                                System.out.println("Jucatorul albastru a castigat!");
                                            }
                                            data.turn = 2;
                                        }
                                        /*System.out.println("Am facut!");
                                        for (int m = 0; m < data.numVertices; m++) {
                                            for (int mn = 0; mn < data.matrix[0].length; mn++) {
                                                System.out.print(data.matrix[m][mn] + " ");
                                            }
                                            System.out.println();
                                        }*/
                                        ok = 1;
                                        break;
                                    }
                                }
                            }
                        }
                        if (ok == 1) {
                            break;
                        }
                    }
                    if (ok == 1) {
                        break;
                    }
                }
            }
            }
        });
    }

    /**
     * Vom verifica daca am desenat un triunghi sau nu
     * Vom crea un map pentru a salva pentru fiecare muchie de culoarea respectiva ce varfuri are.
     * Vom crea 3 for-uri pentru a verifica pentru fiecare combinatie de muchii(care au varfuri[adica sunt de culoarea respectiva] si nu sunt egale) daca exista in matricea de incidenta un caz in care acestea sunt interconectate.
     * @return true-in caz ca a castigat un jucator/false in caz contrar
     */
    private boolean isFinish(){
        Map<Integer, List<Integer>> incidentEdges = new HashMap<>();
        for(int i=0;i<data.matrix[0].length;i++){
            incidentEdges.put(i, new ArrayList<>());
            for(int j=0;j< data.matrix.length;j++){
                if(data.matrix[j][i]==data.turn){
                    incidentEdges.get(i).add(j);
                }
            }
        }
        /*System.out.println(incidentEdges)*/;
        for (int i : incidentEdges.keySet()) {
            if(incidentEdges.get(i).size()>0) {
                for (int j : incidentEdges.keySet()) {
                    if (incidentEdges.get(j).size() > 0 && i!=j) {
                        for (int k : incidentEdges.keySet()) {
                            if (incidentEdges.get(k).size() > 0 && k!=j && i!=k) {
                                if (incidentEdges.get(i).get(0).equals(incidentEdges.get(j).get(0)) || incidentEdges.get(i).get(1).equals(incidentEdges.get(j).get(0)) || incidentEdges.get(i).get(0).equals(incidentEdges.get(j).get(1)) || incidentEdges.get(i).get(1).equals(incidentEdges.get(j).get(1))) {
                                    if (incidentEdges.get(i).get(0).equals(incidentEdges.get(k).get(0)) || incidentEdges.get(i).get(1).equals(incidentEdges.get(k).get(0)) || incidentEdges.get(i).get(0).equals(incidentEdges.get(k).get(1)) || incidentEdges.get(i).get(1).equals(incidentEdges.get(k).get(1))) {
                                        if (incidentEdges.get(k).get(0).equals(incidentEdges.get(j).get(0)) || incidentEdges.get(k).get(1).equals(incidentEdges.get(j).get(0)) || incidentEdges.get(k).get(0).equals(incidentEdges.get(j).get(1)) || incidentEdges.get(k).get(1).equals(incidentEdges.get(j).get(1))) {
                                            data.isItFinished=1;
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public void update(){
        init();
    }

}