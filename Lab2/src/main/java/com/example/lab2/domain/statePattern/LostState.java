package com.example.lab2.domain.statePattern;

public class LostState extends State{
    LostState(Player player){
        super(player);
    }

    @Override
    public String gameOn() {
        return null;
    }

    @Override
    public String gameWon() {
        return null;
    }

    @Override
    public String gameLost() {
        player.setGameOn(false);
        player.setHangCurrentState("\n +---+\n |   |\n O   |\n/|\\  |\n/ \\  |\n     |\nTTTTTT");
        player.setCurrentStateInformation("You lost!");
        return player.getCurrentStateInformation();
    }
}
