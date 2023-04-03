package org.example;
import java.awt.*;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.*;

public class MainFrame extends JFrame implements Serializable {
    Game game;
    ConfigPanel configPasnel;
    ControlPanel controlPanel;
    DrawingPanel canvas;

    public MainFrame(Game g) throws IOException {
        super("My Drawing Application");
        game=g;
        init();
    }

    private void init()  throws IOException {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        configPasnel = new ConfigPanel(this);
        controlPanel = new ControlPanel(this);
        canvas = new DrawingPanel(this);
        add(canvas, BorderLayout.CENTER);
        add(configPasnel, BorderLayout.NORTH);
        add(controlPanel, BorderLayout.SOUTH);
        pack();
    }

}
