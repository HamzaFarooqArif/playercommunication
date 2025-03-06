package com.task.playercommunication.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.task.playercommunication.Message;
import com.task.playercommunication.Player;

/**
 * Server class that acts as Player 1, waiting for Player 2 (Client) to connect.
 */
public class Server extends Node{

    public Server() {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Server started. Waiting for player 2 to connect...");
            
            this.socket = serverSocket.accept();
            this.input = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.output = new PrintWriter(this.socket.getOutputStream(), true);
            this.messages = new ArrayList<>();
            
            System.out.println("Player 2 connected!");

            Player player = new Player("Player 1 (Server)");
            this.gameLoop(player, true); // Player 1 starts first
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
