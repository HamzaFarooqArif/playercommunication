package com.task.playercommunication.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.task.playercommunication.Player;

/**
 * Server class that acts as Player 1, waiting for Player 2 (Client) to connect.
 */
public class Server {
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;

    public Server() {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Server started. Waiting for player 2 to connect...");
            this.socket = serverSocket.accept();
            this.input = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.output = new PrintWriter(this.socket.getOutputStream(), true);

            System.out.println("Player 2 connected!");

            Player player = new Player("Player 1 (Server)");
            gameLoop(player, true); // Player 1 starts first
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void gameLoop(Player player, boolean isInitiator) throws IOException {
        if (isInitiator) {
            sendMessage(player, "Hello");
        }

        while (player.messageCount < 10) {
            String received = receiveMessage(player.name);
            if (received.isEmpty()) break;

            sendMessage(player, received + " " + player.messageCount);
        }

        System.out.println("Game finished. Closing connection...");
        close();
    }

    public String receiveMessage(String name) throws IOException {
        String received = input.readLine();
        if (received != null) {
            System.out.println(name + " received: " + received);
            return received;
        }
        return "";
    }

    public void sendMessage(Player player, String message) {
        player.messageCount++;
        System.out.println(player.name + " sending: " + message);
        output.println(message);
    }

    public void close() throws IOException {
        socket.close();
    }
}
