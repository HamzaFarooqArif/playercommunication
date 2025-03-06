package com.task.playercommunication;

/**
 * Represents a message exchanged between players.
 *
 * Responsibilities:
 * - Stores sender, recipient, and message content.
 * - Provides getter methods to retrieve message details.
 * - Overrides toString() to format message display.
 */
public class Message {
    private final String sender;
    private final String recipient;
    private final String content;

    /**
     * Constructs a new Message object.
     *
     * @param sender    The sender of the message.
     * @param recipient The recipient of the message.
     * @param content   The message content.
     */
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
