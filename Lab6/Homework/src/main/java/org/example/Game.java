package org.example;

import java.io.IOException;
import java.io.Serializable;

/**
 * O clasa de legatura intre componenta logica si componenta grafica a jocului
 */
public class Game implements Serializable {
    MainFrame frame;
    Logic logic;
    Game() throws IOException {
        frame=new MainFrame(this);
        logic=new Logic(this);
        frame.setVisible(true);
    }
}
