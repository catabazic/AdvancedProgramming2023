package org.example;
import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * clasa care va fi drept header a ferestrei noastre de joc
 */
public class ConfigPanel extends JPanel {
    final MainFrame frame;
    JLabel dotsLabel, linesLabel;
    JSpinner dotsSpinner;
    JSpinner linesCombo;
    JButton createButton;
    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }
    private void init() {
        dotsLabel = new JLabel("Number of dots:");
        dotsSpinner = new JSpinner(new SpinnerNumberModel(6, 3, 100, 1));
        linesLabel=new JLabel("Line Probabillity");
        linesCombo=new JSpinner(new SpinnerNumberModel(1.0,0.0,1.0,0.1));
        createButton=new JButton("Create new game");
        add(dotsLabel);
        add(dotsSpinner);
        add(linesLabel);
        add(linesCombo);
        add(createButton);
        createButton.addActionListener(this::createGame);
    }

    private void createGame(ActionEvent e) {
        frame.game.logic.update();
        frame.canvas.update(frame.getGraphics());
    }
    public int dotsNr(){
        return (int)dotsSpinner.getValue();
    }

    public double LinesNr() {
        return (double)linesCombo.getValue();
    }
}