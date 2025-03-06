package com.task.playercommunication;

import com.task.playercommunication.network.Client;
import com.task.playercommunication.network.Server;

/**
 * Main class to start either the Server (Player 1) or Client (Player 2).
 *
 * Responsibilities:
 * - Starts both players in the same process or different processes.
 * - Manages execution flow based on user input.
 */
public class App {
    public static void main(String[] args) {
        if (args.length > 0 && "separate".equalsIgnoreCase(args[0])) {
            System.out.println("[INFO] Running in separate process mode...");
            startSeparateProcess();
        } else {
            System.out.println("[INFO] Running in same process mode...");
            startInSameProcess();
        }
    }

    /**
     * Runs the server and client in the same process using separate threads.
     */
    private static void startInSameProcess() {
        Thread serverThread = new Thread(() -> {
            new Server();
        });

        Thread clientThread = new Thread(() -> {
            try {
                new Client();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        serverThread.start();
        clientThread.start();
    }

    /**
     * Runs the server and client in separate processes.
     */
    private static void startSeparateProcess() {
        try {
            new Client();
        } catch (Exception e) {
            try {
                new Server();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
