package com.task.playercommunication;

import java.io.*;
import java.net.*;

/**
 * Represents a Player in the socket communication game.
 */
public class Player {
    public int messageCount = 0;
    public String name;

    public Player(String name) throws IOException {
        this.name = name;
    }
}
