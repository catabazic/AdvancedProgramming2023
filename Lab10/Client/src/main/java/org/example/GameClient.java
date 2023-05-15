package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class GameClient {
    private final String serverAddress;
    private final int PORT;

    private Socket socket;

    // For responses coming from the server
    BufferedReader input;

    public GameClient(String serverAddress, int PORT) {
        this.serverAddress = serverAddress;
        this.PORT = PORT;
    }

    public void connect() throws IOException {
        socket = new Socket(serverAddress, PORT);

        PrintWriter out = new PrintWriter(socket.getOutputStream());
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Scanner request = new Scanner(System.in);

        welcomeMessage();
        String command = new String();
        do {
            System.out.print("Input command: ");
            command = request.nextLine();
            System.out.println("Read the request: '" + command + "'");

            // Send the request to the server
            out.println(command);
            out.flush();

            // Wait for the response
            getResponse();
        } while (!command.equals("exit"));
        socket.close();
    }

    private void welcomeMessage() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("\n----------------------------Gomoku!----------------------------\n");
        System.out.println("---------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------");
        System.out.println("Accepted commands: ");
        System.out.println("-join game");
        System.out.println("-start");
        System.out.println("-try move [row] [column]");
        System.out.println("-submit move");
        System.out.println("-exit");
        System.out.println("---------------------------------------------------------------\n\n");
    }

    private void getResponse() throws IOException {
        String inputLine;
        while (!(inputLine = input.readLine()).equals("done")) {
            System.out.println("Server response: '" + inputLine);
        }
       // String response = input.readLine();
    }
}
