package com.task.playercommunication.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import com.task.playercommunication.Message;
import com.task.playercommunication.Player;

public abstract class Node {
    Socket socket;
    BufferedReader input;
    PrintWriter output;
    Player sender;
    Player reciever;
    List<Message> messages;

    String receiveMessage() throws IOException {
        String received = input.readLine();
        if (received != null) {
            System.out.println(sender.getName() + " received: " + received);
            return received;
        }
        return "";
    }

    void sendMessage(Message message) {
        this.messages.add(message);
        System.out.println(message);
        output.println(message);
    }

    void gameLoop(boolean isInitiator) throws IOException {
        if (isInitiator) {
            Message msg = new Message(sender.getName(), reciever.getName(), "Hello");
            sendMessage(msg);
        }

        while (messages.size() < 10) {
            String received = receiveMessage();
            if (received.isEmpty()) break;

            Message msg = new Message(sender.getName(), reciever.getName(), messages.size()+"");
            sendMessage(msg);
        }

        System.out.println("Game finished. Closing connection...");
        close();
    }

    void close() throws IOException {
        socket.close();
    }
}
