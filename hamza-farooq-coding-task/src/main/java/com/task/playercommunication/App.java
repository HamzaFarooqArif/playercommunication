package com.task.playercommunication;

import com.task.playercommunication.network.Client;
import com.task.playercommunication.network.Server;

/**
 * Main class to start either the Server (Player 1) or Client (Player 2).
 */
public class App {
    public static void main(String[] args) {
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
