package com.task.playercommunication;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws InterruptedException {

        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");

        // Player1 starts the conversation by sending a message to Player2
        player1.sendMessage("Player2", "Hello, Player2!");
    }
}
