package com.task.playercommunication;

import java.io.*;
import java.net.*;

/**
 * Represents a Player in the socket communication game.
 * Each player can send and receive messages from another player.
 */

/**
 * Main class that initializes two players and starts the communication.
 */
public class App {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Server started.");

            // Create two interconnected sockets within the same process
            Socket player1Socket = new Socket("localhost", 5000);
            Socket player2Socket = serverSocket.accept();

            Player player1 = new Player("Player 1", player1Socket);
            Player player2 = new Player("Player 2", player2Socket);

            // Start the game loop
            gameLoop(player1, player2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void gameLoop(Player initiator, Player responder) throws IOException {
        String message = "Hello";
        initiator.sendMessage(message);

        while (initiator.getMessageCount() < 10) {
            String received = responder.receiveMessage();
            if (received.isEmpty()) break;

            responder.sendMessage(received + " " + responder.getMessageCount());
            received = initiator.receiveMessage();
            if (received.isEmpty()) break;

            initiator.sendMessage(received + " " + initiator.getMessageCount());
        }

        System.out.println("Game finished. Closing connections...");
        initiator.close();
        responder.close();
    }
}