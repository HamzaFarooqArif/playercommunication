package com.task.playercommunication;

/**
 * Represents a message with sender, recipient, and content.
 */
public class Message {
    private final String sender;
    private final String recipient;
    private final String content;

    public Message(String sender, String recipient, String content) {
        this.sender = sender;
        this.recipient = recipient;
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return sender + " -> " + recipient + ": " + content;
    }
}
