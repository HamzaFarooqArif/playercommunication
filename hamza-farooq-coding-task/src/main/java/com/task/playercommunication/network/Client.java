package com.task.playercommunication.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.task.playercommunication.Constants;
import com.task.playercommunication.Player;

/**
 * Client class that acts as Player 2, connecting to Player 1 (Server).
 */
public class Client extends Node {
    
    public Client() throws Exception {
        super();
        try (Socket socket = new Socket(Constants.hostname, Constants.port)) {
            System.out.println("Connected to Player 1!");
            
            this.socket = socket;
            this.input = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.output = new PrintWriter(this.socket.getOutputStream(), true);
            this.messages = new ArrayList<>();

            this.sender = new Player(Constants.Player2);
            this.reciever = new Player(Constants.Player1);

            this.gameLoop(false);
        } catch (IOException e) {
            throw new Exception(e);
        }
    }
}
