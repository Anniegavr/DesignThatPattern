package com.example.lab2.domain.statePattern;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameOn extends State {

    GameOn(Player player) {
        super(player);
    }

    @Override
    public String gameOn() {
        player.setCurrentStateInformation("You have "+player.getLives()+" lives left\n"+player.getGuessedLetters()+player.getHangCurrentState());
        return player.getCurrentStateInformation();
    }

    @Override
    public String gameWon() {
        return "";
    }

    @Override
    public String gameLost() {
        return "";
    }
}
