package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static org.example.GameServer.printWriterLock;

public class ClientThread extends Thread {
    private final Socket socket;
    Player player;
    PrintWriter output;
    int ok;

    public ClientThread(Socket socket) {
        this.socket = socket;
        player = new Player();
    }

    @Override
    public void run() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // Read the request and send a response UNTIL THE CLIENT CLOSES THE CONNECTION
            do {/*else if(player.getGame() != null && player.getGame().getTurn() != 0 && player.getGame().getTurn() != player.getColor()){
                    wait();
                }*/
                sendResponse(input);
            } while (!socket.isClosed());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendResponse(BufferedReader input) throws IOException {
        output = new PrintWriter(socket.getOutputStream());
        String request = input.readLine();
        System.out.println("Read request '" + request + "'");
        if (request == null) {
            return;
        }
        String[] requestContent = new String[10];
        requestContent = request.split(" ");
        switch (requestContent[0].toLowerCase()) {
            case "exit" -> {
                synchronized (printWriterLock) {
                    output.println("Server stopped");
                    output.close();
                    output.flush();
                }
                System.out.println("Connection terminated!");
                System.exit(0);
            }
            /*case "create" -> {
                Command.createGame();
                synchronized (printWriterLock) {
                    output.println("Game created successfully!");
                }
            }*/
            case "join" -> {
                if (requestContent.length == 2 && requestContent[1].equals("game")) {
                    this.writeToClient(Command.joinGame(player));
                } else {
                    output.println("Bad request!");
                }
            }
            case "start" -> {
                synchronized (printWriterLock) {
                    output.println("Waiting...");
                    output.flush();
                }
                Command com = new Command();
                this.writeToClient(com.startGame(player));
                ok = 1;
            }
            case "submit" -> {
                if (requestContent.length == 2 && requestContent[1].equals("move")) {
                    String response = Command.submitMove(player);
                    this.writeToClient(response);
                    if ("Move was submitted!".equals(response)) {
                        ok = 1;
                    } else if (response.equals("Game is finished!")) {
                        if (player.getGame().getWinner() == player.getColor()) {
                            this.writeToClient("You win!");
                        } else {
                            this.writeToClient("You lose!");
                        }
                    }
                } else {
                    output.println("Bad request!");
                }
            }
            case "try" -> {
                try {
                    if (requestContent.length == 4) {
                        int x = Integer.parseInt(requestContent[2]);
                        int y = Integer.parseInt(requestContent[3]);
                        if (requestContent[1].toLowerCase().equals("move")) {
                            String responce = Command.tryMove(player, x, y);
                            this.writeToClient(responce);
                        }
                    } else {
                        output.println("Bad request!");
                    }
                } catch (NumberFormatException e) {
                    synchronized (printWriterLock) {
                        output.println("Bad request!");
                    }
                }
                ok = 0;
            }
            default -> {
                synchronized (printWriterLock) {
                    output.println("Bad request!");
                }
            }
        }
        while (player.getGame() != null && player.getGame().getTurn() != 0) {
            if (player.getGame() != null && player.getGame().getWinner() != 0 && ok == 1) {
                if (player.getGame().getWinner() == player.getColor()) {
                    this.writeToClient("You win!");
                } else {
                    this.writeToClient("You lose!");
                }
                break;
            } else if (player.getGame() != null && player.getGame().getTurn() == player.getColor() && player.getGame().getTurn() != 0) {
                synchronized (printWriterLock) {
                    output.println("It's your turn!\n");
                    output.println("Here is your board: \n\n");
                    output.println(player.getGame().getMatrix());
                    output.flush();
                }
                break;
            }
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        synchronized (printWriterLock) {
            output.println("done");
            output.flush();
        }
    }


    private void writeToClient(String out) {
        output.println(out);
        output.flush();
    }

    public Player getPlayer() {
        return player;
    }
}
