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

            
            Socket playerSocket = this.socket;
            System.out.println("Player 2 connected!");

            Player player = new Player("Player 1 (Server)", playerSocket);
            gameLoop(player, true); // Player 1 starts first
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void gameLoop(Player player, boolean isInitiator) throws IOException {
        if (isInitiator) {
            player.sendMessage("Hello");
        }

        while (player.getMessageCount() < 10) {
            String received = player.receiveMessage();
            if (received.isEmpty()) break;

            player.sendMessage(received + " " + player.getMessageCount());
        }

        System.out.println("Game finished. Closing connection...");
        player.close();
    }
}
