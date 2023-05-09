package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread {
    private final Socket socket;
    private BufferedReader input;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            // Read the information coming into the socket from the client
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream());

            // Read the request and send a response UNTIL THE CLIENT CLOSES THE CONNECTION
            do {
                String request = input.readLine();
                // Logica serverului
                String response = request + " hello word!";
                output.println(response);
                output.flush();

                sendResponse();
            } while (!socket.isClosed());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendResponse() throws IOException {
        String request = input.readLine();
        // System.out.println("Read request '" + request + "'");
        if (request == null) {
            return;
        }
        // Create a PrintWriter for writing information from the server to the client
        PrintWriter output = new PrintWriter(socket.getOutputStream());

        switch (request) {
            case "stop" -> {
                output.println("Server stopped");
                output.close();
                output.flush();
                System.out.println("Connection terminated!");
                System.exit(0);
            }
            default -> {
                output.println("Server received the request ...");
            }
        }
        output.flush();
    }
}
