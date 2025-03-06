package com.task.playercommunication;

public class Player {
    private final String name;

    /**
     * Constructs a new Player object.
     *
     * @param name The name of the player.
     */
    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
