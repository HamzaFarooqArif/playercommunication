package com.task.playercommunication.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.task.playercommunication.Player;

/**
 * Client class that acts as Player 2, connecting to Player 1 (Server).
 */
public class Client {
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;
    
    public Client() throws Exception {
        super();
        try (Socket socket = new Socket("localhost", 5000)) {
            System.out.println("Connected to Player 1!");
            
            this.socket = socket;
            this.input = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.output = new PrintWriter(this.socket.getOutputStream(), true);
            
            Player player = new Player("Player 2 (Client)", socket);
            gameLoop(player);
        } catch (IOException e) {
            throw new Exception(e);
        }
    }

    private void gameLoop(Player player) throws IOException {
        while (player.getMessageCount() < 10) {
            String received = player.receiveMessage();
            if (received.isEmpty()) break;

            player.sendMessage(received + " " + player.getMessageCount());
        }

        System.out.println("Game finished. Closing connection...");
        player.close();
    }
}
