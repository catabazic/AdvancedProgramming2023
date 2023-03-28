package org.example;
import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {
    ConfigPanel configPasnel;
    ControlPanel controlPanel;
    DrawingPanel canvas;

    public MainFrame() {
        super("My Drawing Application");
        init();
    }

    private void init() {
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
