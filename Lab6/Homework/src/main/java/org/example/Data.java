package org.example;

import java.io.Serializable;

/**
 * Cream o clasa cu toate datele jocului pentru a putea folosi serializarea si prin urmarea reincarcarea jocului!
 * board-matricea de adiacenta a grafului dat
 * matrix-matricea de incidenta a jocului
 * x,y - array de coordonate a fiecarui varf a grafului
 * turn - cine trebuie sa mearga (jucatorul rosu sau albastru)
 * isItFinished- daca jocul e terminat sau nu
 * numVertices- numarul de varfuri
 * probVertices- cat de des vor fi conectate intre ele varfurile
 */
public class Data implements Serializable {
     int [][] board;
     int [][] matrix;
     int[] x,y;
    static int turn;
    int isItFinished;
    int numVertices;
    double probVertices;
}
