package com.task.playercommunication.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.task.playercommunication.Constants;
import com.task.playercommunication.Message;
import com.task.playercommunication.Player;

/**
 * Server class representing Player 1, responsible for accepting Player 2's connection.
 *
 * Responsibilities:
 * - Starts a server socket and waits for client connection.
 * - Manages message exchange with the connected client.
 * - Implements game loop logic for Player 1.
 */
public class Server extends Node{

    /**
     * Initializes the Server and waits for a Client connection.
     */
    public Server() {
        try (ServerSocket serverSocket = new ServerSocket(Constants.port)) {
            System.out.println("[INFO] Server PID: " + ProcessHandle.current().pid() + ", Thread: " + Thread.currentThread().getName());
            System.out.println("Server started. Waiting for player 2 to connect...");
            
            this.socket = serverSocket.accept();
            this.input = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.output = new PrintWriter(this.socket.getOutputStream(), true);
            
            this.sender = new Player(Constants.Player1);
            this.reciever = new Player(Constants.Player2);
            
            System.out.println(sender.getName() + " Connected to " + reciever.getName());
            
            this.gameLoop(true); // Player 1 starts first
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
