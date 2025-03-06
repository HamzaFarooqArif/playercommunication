package com.task.playercommunication;

import com.task.playercommunication.network.Server;
import com.task.playercommunication.network.Client;

/**
 * Runs both the Server and Client in the same Java process.
 */
public class App {
    public static void main(String[] args) {
        // Start the server in a separate thread
        Thread serverThread = new Thread(() -> {
            try {
                new Server();
            } catch (Exception e) {
                System.out.println("[ERROR] Server failed to start: " + e.getMessage());
            }
        });

        serverThread.start(); // Run Server in a separate thread

        // Wait a bit for the server to start before launching client
        try {
            Thread.sleep(2000); // Give server time to initialize
        } catch (InterruptedException ignored) {}

        // Start the client in the main thread
        try {
            new Client();
        } catch (Exception e) {
            System.out.println("[ERROR] Client failed to start: " + e.getMessage());
        }
    }
}
