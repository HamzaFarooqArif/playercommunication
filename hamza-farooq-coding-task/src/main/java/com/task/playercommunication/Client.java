package com.task.playercommunication;

import java.io.IOException;
import java.net.Socket;

/**
 * Client class that acts as Player 2, connecting to Player 1 (Server).
 */
public class Client {
    public Client() throws Exception {
        super();
        try (Socket socket = new Socket("localhost", 5000)) {
            System.out.println("Connected to Player 1!");

            Player player = new Player("Player 2 (Client)", socket);
            gameLoop(player);
        } catch (IOException e) {
            throw new Exception(e);
        }
    }

    private void gameLoop(Player player) throws IOException {
        while (player.getMessageCount() < 10) {
            String received = player.receiveMessage();
            if (received.isEmpty()) break;

            player.sendMessage(received + " " + player.getMessageCount());
        }

        System.out.println("Game finished. Closing connection...");
        player.close();
    }
}
