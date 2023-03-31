package org.example;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class ViewCommand implements Commands{
    /**
     * Folosim clasa Desktop pentru a deschide fisierul cu calea specificata
     * @param URL calea catre fisierul pe care ne dorim sa il deschidem
     * @throws IOException exceptie in caz ca nu reusim sa deschidem fisierul
     */
    public static void run(String URL) throws IOException {
        Desktop d= Desktop.getDesktop();
        File f=new File(URL);
        d.open(f);
    }
}
