package com.task.playercommunication.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import com.task.playercommunication.Player;

public abstract class Node {
    Socket socket;
    BufferedReader input;
    PrintWriter output;
    List<String> messages;

    private String receiveMessage(String name) throws IOException {
        String received = input.readLine();
        if (received != null) {
            System.out.println(name + " received: " + received);
            return received;
        }
        return "";
    }

    private void sendMessage(Player player, String message) {
        this.messages.add(message);

        System.out.println(player.getName() + " sending: " + message);
        output.println(message);
    }

    private void gameLoop(Player player, boolean isInitiator) throws IOException {
        if (isInitiator) {
            sendMessage(player, "Hello");
        }

        while (messages.size() < 10) {
            String received = receiveMessage(player.getName());
            if (received.isEmpty()) break;

            sendMessage(player, received + " " + messages.size());
        }

        System.out.println("Game finished. Closing connection...");
        close();
    }

    public void close() throws IOException {
        socket.close();
    }
}
