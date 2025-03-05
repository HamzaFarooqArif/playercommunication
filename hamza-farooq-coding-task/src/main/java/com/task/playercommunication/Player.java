package com.task.playercommunication;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * The Player class represents a player that can send and receive messages.
 * Uses BlockingQueue to store received messages and processes them sequentially.
 */
public class Player implements MessageObserver {
    private final String name;
    private final MessageDispatcher dispatcher;
    private final BlockingQueue<Message> messageQueue = new LinkedBlockingQueue<>();
    private int messageCount = 0;

    public Player(String name, MessageDispatcher dispatcher) {
        this.name = name;
        this.dispatcher = dispatcher;
        this.dispatcher.registerPlayer(name, this); // Register with dispatcher
        startMessageProcessing(); // Start processing received messages
    }

    public void sendMessage(String recipient, String message) {
        if (messageCount < Constants.MESSAGE_LIMIT) {
            Message msg = new Message(name, recipient, message);
            System.out.println(msg);
            messageCount++;
            dispatcher.sendMessage(msg);
        }
    }

    @Override
    public void onMessageReceived(Message message) {
        try {
            messageQueue.put(message); // Store message in queue
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void startMessageProcessing() {
        new Thread(() -> {
            while (messageCount < Constants.MESSAGE_LIMIT) {
                try {
                    Message receivedMessage = messageQueue.take(); // Block until message arrives
                    System.out.println(name + " <- " + receivedMessage);
                    sendMessage(receivedMessage.getSender(), "Reply " + messageCount);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }).start();
    }
}
