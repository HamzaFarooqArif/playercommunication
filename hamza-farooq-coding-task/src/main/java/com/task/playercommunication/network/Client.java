package com.task.playercommunication.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.task.playercommunication.Constants;
import com.task.playercommunication.Player;

/**
 * Client class representing Player 2, responsible for connecting to Player 1 (Server).
 *
 * Responsibilities:
 * - Establishes a connection to the server.
 * - Manages message exchange.
 * - Implements game loop logic for Player 2.
 */
public class Client extends Node {
    
    /**
     * Initializes the Client and connects to the Server.
     *
     * @throws Exception if an error occurs during connection setup.
     */
    public Client() throws Exception {
        super();
        try (Socket socket = new Socket(Constants.hostname, Constants.port)) {
            System.out.println("[INFO] Server PID: " + ProcessHandle.current().pid() + ", Thread: " + Thread.currentThread().getName());
            
            this.socket = socket;
            this.input = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.output = new PrintWriter(this.socket.getOutputStream(), true);
           
            this.sender = new Player(Constants.Player2);
            this.reciever = new Player(Constants.Player1);
            
            System.out.println(sender.getName() + " Connected to " + reciever.getName());
            this.gameLoop(false);
        } catch (IOException e) {
            throw new Exception(e);
        }
    }
}
