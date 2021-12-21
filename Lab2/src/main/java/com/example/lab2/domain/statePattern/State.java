package com.example.lab2.domain.statePattern;

abstract class State {
    Player player;

    State(Player player) {
        this.player = player;
    }

    public abstract String gameOn();
    public abstract String gameWon();
    public abstract String gameLost();
}
