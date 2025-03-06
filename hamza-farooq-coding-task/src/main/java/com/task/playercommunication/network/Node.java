package com.task.playercommunication.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import com.task.playercommunication.Constants;
import com.task.playercommunication.Message;
import com.task.playercommunication.Player;

/**
 * Abstract base class for both Client and Server.
 *
 * Responsibilities:
 * - Handles socket communication.
 * - Manages message sending and receiving.
 * - Implements the game loop logic.
 */
public abstract class Node {
    Socket socket;
    BufferedReader input;
    PrintWriter output;
    Player sender;
    Player reciever;
    int messageCount = 0;

    /**
     * Receives a message from the connected player.
     *
     * @return The received message content.
     * @throws IOException If an error occurs during reading.
     */
    String receiveMessage() throws IOException {
        String received = input.readLine();
        if (received != null) {
            System.out.println(sender.getName() + " received: " + received);
            messageCount++;
            return received;
        }
        return "";
    }

    /**
     * Sends a message to the connected player.
     *
     * @param message The message to send.
     */
    void sendMessage(Message message) {
        System.out.println(message);
        output.println(message.getContent());
    }

    /**
     * Implements the message exchange logic between players.
     *
     * @param isInitiator True if this player starts the communication.
     * @throws IOException If an error occurs during communication.
     */
    void gameLoop(boolean isInitiator) throws IOException {
        if (isInitiator) {
            Message msg = new Message(sender.getName(), reciever.getName(), "0");
            sendMessage(msg);
        }

        while (messageCount < Constants.MESSAGE_LIMIT - 1) {
            String received = receiveMessage();
            if (received.isEmpty()) break;

            String content = received + " " + messageCount;

            Message msg = new Message(sender.getName(), reciever.getName(), content);
            sendMessage(msg);
        }

        System.out.println("Game finished. " + sender.getName() +" Closing connection...");
        close();
    }

    /**
     * Closes the socket connection.
     *
     * @throws IOException If an error occurs during closing.
     */
    void close() throws IOException {
        socket.close();
    }
}
