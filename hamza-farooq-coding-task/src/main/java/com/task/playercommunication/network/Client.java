package com.task.playercommunication.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.task.playercommunication.Player;

/**
 * Client class that acts as Player 2, connecting to Player 1 (Server).
 */
public class Client {
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;

    private List<String> messages;
    
    public Client() throws Exception {
        super();
        try (Socket socket = new Socket("localhost", 5000)) {
            System.out.println("Connected to Player 1!");
            
            this.socket = socket;
            this.input = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.output = new PrintWriter(this.socket.getOutputStream(), true);
            this.messages = new ArrayList<>();

            Player player = new Player("Player 2 (Client)");
            gameLoop(player);
        } catch (IOException e) {
            throw new Exception(e);
        }
    }

    private void gameLoop(Player player) throws IOException {
        while (messages.size() < 10) {
            String received = receiveMessage(player.getName());
            if (received.isEmpty()) break;

            sendMessage(player, received + " " + messages.size());
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
        messages.add(message);
        System.out.println(player.getName() + " sending: " + message);
        output.println(message);
    }

    public void close() throws IOException {
        socket.close();
    }
}
