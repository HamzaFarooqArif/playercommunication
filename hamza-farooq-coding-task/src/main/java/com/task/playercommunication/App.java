package com.task.playercommunication;

/**
 * Main class to start either the Server (Player 1) or Client (Player 2).
 */
public class App {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java com.task.playercommunication.App <server|client>");
            return;
        }

        try {
            if (args[0].equalsIgnoreCase("server")) {
                new Server(); // Start the server
            } else if (args[0].equalsIgnoreCase("client")) {
                new Client(); // Start the client
            } else {
                System.out.println("Invalid argument! Use 'server' or 'client'.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
