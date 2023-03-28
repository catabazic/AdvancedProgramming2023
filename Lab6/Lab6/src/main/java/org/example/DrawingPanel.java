package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class DrawingPanel extends JPanel {
    final MainFrame frame;
    final static int W = 800, H = 600;
    private int numVertices;
    private double edgeProbability;
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
                createBoard();
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
        numVertices = frame.configPasnel.dotsNr();
        edgeProbability = frame.configPasnel.LinesNr();
        createOffscreenImage();
        createVertices();
        drawLines();
        drawVertices();
        repaint();
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
    private void drawLines() {
        graphics.setColor(Color.MAGENTA);
        for(int i=0;i<numVertices;i++){
            for(int j=0;j<edgeProbability*numVertices;j++){
                graphics.drawLine(x[i],y[i],x[(i+j)%numVertices],y[(i+j)%numVertices]);
            }
        }
    }
    private void drawVertices() {
        graphics.setColor(Color.BLACK);
        for(int i=0;i<numVertices;i++){
            graphics.fillOval(x[i]-5,y[i]-5,10,10);
        }
    }
    @Override
    public void update(Graphics g) { } //No need for update

    //Draw the offscreen image, using the original graphics
    @Override
    protected void paintComponent(Graphics graphics) {
        graphics.drawImage(image, 0, 0, this);
    }

}
