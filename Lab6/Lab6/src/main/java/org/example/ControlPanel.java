package org.example;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    JButton exitBtn = new JButton("Exit");
    JButton loadBtn = new JButton("Load");
    JButton saveBtn = new JButton("Save");
    JButton resetBtn = new JButton("Reset");
    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }
    private void init() {
        add(exitBtn);
        add(loadBtn);
        add(saveBtn);
        add(resetBtn);
        exitBtn.addActionListener(this::exitGame);
        /*loadBtn.addActionListener(this::loadGame);
        saveBtn.addActionListener(this::saveGame);
        resetBtn.addActionListener(this::resetGame);*/
    }
    private void exitGame(ActionEvent e) {
        frame.dispose();
    }

    /*private void loadGame(ActionEvent e){

    }

    private void saveGame(ActionEvent e){
        frame.dispose();
    }

    private void resetGame(ActionEvent e){
        frame.dispose();
    }*/
}

