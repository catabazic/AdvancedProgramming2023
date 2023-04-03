package org.example;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.*;

/**
 * clasa care va fi drept footer a ferestrei de joc
 */
public class ControlPanel extends JPanel implements Serializable {
    final MainFrame frame;
    JButton exitBtn = new JButton("Exit");
    JButton loadBtn = new JButton("Load");
    JButton saveBtn = new JButton("Save");
    JButton saveImgBtn = new JButton("Save Image");
    public ControlPanel(MainFrame frame) throws IOException{
        this.frame = frame;
        init();
    }
    private void init() throws IOException {
        add(saveBtn);
        add(loadBtn);
        add(saveImgBtn);
        add(exitBtn);
        exitBtn.addActionListener(this::exitGame);
        loadBtn.addActionListener(this::loadGame);
        saveBtn.addActionListener(this::saveGame);
        saveImgBtn.addActionListener(this::saveImgGame);
    }

    private void exitGame(ActionEvent e) {
        frame.dispose();
    }

    /**
     * vom folosi clasele FileInputStream si ObjectInputStream pentru a refolosi datele deja salvate
     * este posibila refolosirea datelor doar in caz ca graful folosit acum respecta graful care e salvat(adica are acelasi numar de varfusi si aceeasi probabilitate)
     * @param e
     */
    private void loadGame(ActionEvent e) {
        try {
            FileInputStream fileIn = new FileInputStream("game_state.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Data d=(Data) in.readObject();
            if(d.numVertices==frame.game.logic.data.numVertices && d.probVertices==frame.game.logic.data.probVertices) {
                frame.game.logic.data = d;
                in.close();
                fileIn.close();
                frame.canvas.update(frame.getGraphics());
            }
        }catch (IOException i) {
            i.printStackTrace();
        }catch (ClassNotFoundException c){
            c.printStackTrace();
        }
    }

    /**
     * vom folosi clasele FileOutputStream si ObjectOutputStream pentru a pastra datele despre jocul nostru
     * @param e
     */
    public void saveGame(ActionEvent e) {
        try {
            FileOutputStream fileOut = new FileOutputStream("game_state.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(frame.game.logic.data);
            out.close();
            fileOut.close();
            System.out.println("Game state saved to game_state.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    private void saveImgGame(ActionEvent e){
        frame.canvas.paint(frame.canvas.graphics);
        try {
            ImageIO.write(frame.canvas.image, "png", new File("gameboard.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

