package com.task.playercommunication;

import java.io.*;
import java.net.*;

/**
 * Represents a Player in the socket communication game.
 * Each player can send and receive messages from another player.
 */
public class Player {
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;
    private int messageCount = 0;
    private String name;

    public Player(String name, Socket socket) throws IOException {
        this.name = name;
        this.socket = socket;
        this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.output = new PrintWriter(socket.getOutputStream(), true);
    }

    public void sendMessage(String message) {
        messageCount++;
        System.out.println(name + " sending: " + message);
        output.println(message);
    }

    public String receiveMessage() throws IOException {
        String received = input.readLine();
        if (received != null) {
            System.out.println(name + " received: " + received);
            return received;
        }
        return "";
    }

    public boolean isConnected() {
        return !socket.isClosed();
    }

    public int getMessageCount() {
        return messageCount;
    }

    public void close() throws IOException {
        socket.close();
    }
}
