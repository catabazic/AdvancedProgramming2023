package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class DrawingPanel extends JPanel {
    final MainFrame frame;
    final static int W = 800, H = 600;
    int numVertices;
    double edgeProbability;
    JLabel finish;
    private int[] x, y;
    BufferedImage image;
    Graphics2D graphics;

    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        createOffscreenImage();
        initPanel();
        createBoard();
    }
    private void initPanel() {
        setPreferredSize(new Dimension(W, H));
        setBorder(BorderFactory.createEtchedBorder());
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

            }
        });
    }
    private void createOffscreenImage() {
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setBackground(Color.BLACK);
        graphics.fillRect(0, 0, 800, 600);
    }
    final void createBoard() {
        System.out.println(graphics.getFont());
        numVertices = frame.configPasnel.dotsNr();
        edgeProbability = frame.configPasnel.LinesNr();
        createOffscreenImage();
        createVertices();
        drawLines();
        drawVertices();
        repaint();
    }

    /**
     * Vom folosi functia asta pentru cazul in care reincarcam un joc salvat recent pentru a desena liniile colorate daca este cazul
     * Primul for il folosim pentru a parcurge matricea pe linii dupa care pe coloane
     * Verificam daca coordonatele i si j a matricii sunt egale cu 2(rosu) sau 3(albastru)
     * In caz ca da, verificam daca e prima data pe coloana cand gasim cifra coresponzatoare si ii signam lui x1 sau y1 dupa caz
     * Daca am ajuns la sfarsitul coloanei si count este 2 sau 3, desenam liniile pentru varfurile salvate recent.
     */
    private void loadVertices(){
        int count=0;
        int x1=0,y1=0;
        for(int i=0;i<frame.game.logic.data.matrix[0].length;i++){
            for(int j=0;j<frame.game.logic.data.matrix.length;j++){
                if(frame.game.logic.data.matrix[j][i]==2){
                    if(count==0){
                        x1=j;
                        count=1;
                    }else{
                        y1=j;
                        count=2;
                    }
                }else if(frame.game.logic.data.matrix[j][i]==3){
                    if(count==0){
                        x1=j;
                        count=1;
                    }else{
                        y1=j;
                        count=3;
                    }
                }
                if(j==frame.game.logic.data.matrix.length-1){
                    if(count==2){
                        count=0;
                        graphics.setColor(Color.RED);
                        graphics.drawLine(x[y1], y[y1], x[x1], y[x1]);
                    }else if(count==3){
                        count=0;
                        graphics.setColor(Color.BLUE);
                        graphics.drawLine(x[y1], y[y1], x[x1], y[x1]);
                    }
                }
            }
        }
    }
    private void createVertices() {
        int x0 = W / 2;
        int y0 = H / 2; //middle of the board
        int radius = H / 2 - 10; //board radius
        double alpha = 2 * Math.PI / numVertices; // the angle
        x = new int[numVertices];
        y = new int[numVertices];
        for (int i = 0; i < numVertices; i++) {
            x[i] = x0 + (int) (radius * Math.cos(alpha * i));
            y[i] = y0 + (int) (radius * Math.sin(alpha * i));
        }
    }

    /**
     * obtinem matricea de adiacenta pentru graful dat
     * prin for-uri parcurdem matricea de adiacent asi in caz ca exista o muchie intre cele doua punce desenam o linie
     */
    private void drawLines() {
        graphics.setColor(Color.BLACK);
        int[][] matrix = GrafKRegulat.getMatrix((int) (edgeProbability * numVertices) - 1, numVertices);
        for (int i = 0;i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (matrix[i][j] == 1) {
                    graphics.drawLine(x[i], y[i], x[(j) % numVertices], y[(j) % numVertices]);
                }
            }
        }
    }

    /**
     * pentru fiecare varf desenam cate un cerc umplut
     */
    private void drawVertices() {
        graphics.setColor(Color.BLACK);
        for(int i=0;i<numVertices;i++){
            graphics.fillOval(x[i]-5,y[i]-5,10,10);
        }
    }

    /**
     * Stergem label-ul cu anuntarea castigatorului dupa caz, cream o alta tabla si adaugam muchiile colorate in caz ca e nevoie
     * @param g the <code>Graphics</code> context in which to paint
     */
    @Override
    public void update(Graphics g) {
        if(finish!=null) {
            remove(finish);
        }
        createBoard();
        loadVertices();
    } //No need for update

    public int[] getXLines() {
        return x;
    }

    public int[] getYLines() {
        return y;
    }

    /**
     * Scriem pe tabla cine a castigat
     * @param i 2-jucatorul rosu; 3-jucatorul albastru
     */
    public void jocTerminat(int i){
        if(i==2){
            finish=new JLabel("Jucatorul rosu a castigat!");
        }else{
            finish=new JLabel("Jucatorul albastru a castigat!");
        }
        add(finish,BorderLayout.CENTER);
        this.repaint();
    }
}
