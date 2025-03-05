package com.task.playercommunication;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws InterruptedException {
        MessageDispatcher dispatcher = new MessageDispatcher();

        Player player1 = new Player("Player1", dispatcher);
        Player player2 = new Player("Player2", dispatcher);

        // Player1 starts the conversation by sending a message to Player2
        player1.sendMessage("Player2", "Hello, Player2!");
    }
}
