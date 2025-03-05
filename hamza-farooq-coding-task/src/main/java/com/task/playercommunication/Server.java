package com.task.playercommunication;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;

/**
 * The Server class acts as a message relay between players.
 * - Accepts connections from players.
 * - Receives messages and forwards them to the correct recipient.
 */
public class Server {
    private static final int PORT = 5000;
    private static final ConcurrentHashMap<String, Socket> clients = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        System.out.println("Server started on port " + PORT);
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler implements Runnable {
        private final Socket socket;
        private String playerName;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

                // First message from client should be its name
                playerName = in.readLine();
                clients.put(playerName, socket);
                System.out.println(playerName + " connected.");

                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println("Received: " + message);
                    String[] parts = message.split(":", 2);
                    if (parts.length == 2) {
                        String recipient = parts[0];
                        String msg = parts[1];
                        sendMessage(recipient, playerName + ": " + msg);
                    }
                }
            } catch (IOException e) {
                System.out.println(playerName + " disconnected.");
            } finally {
                clients.remove(playerName);
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void sendMessage(String recipient, String message) {
            if (clients.containsKey(recipient)) {
                try {
                    PrintWriter out = new PrintWriter(clients.get(recipient).getOutputStream(), true);
                    out.println(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Recipient " + recipient + " not found.");
            }
        }
    }
}
