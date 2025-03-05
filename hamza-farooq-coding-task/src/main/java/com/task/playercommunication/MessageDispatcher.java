package com.task.playercommunication;

import java.util.HashMap;
import java.util.Map;

/**
 * MessageDispatcher routes messages between specific players.
 */
public class MessageDispatcher {
    private final Map<String, MessageObserver> observers = new HashMap<>();

    public void registerPlayer(String name, MessageObserver observer) {
        observers.put(name, observer);
    }

    public void sendMessage(Message message) {
        if (observers.containsKey(message.getRecipient())) {
            observers.get(message.getRecipient()).onMessageReceived(message);
        } else {
            System.out.println("Message could not be delivered: " + message.getRecipient() + " not found.");
        }
    }
}
