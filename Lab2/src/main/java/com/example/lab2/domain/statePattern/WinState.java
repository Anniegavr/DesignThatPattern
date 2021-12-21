package com.example.lab2.domain.statePattern;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WinState extends State {
    WinState(Player player){
        super(player);
    }

    @Override
    public String gameOn() {
        return null;
    }

    @Override
    public String gameWon() {
        player.setGameOn(false);
        return "You won! 😀\n";
    }

    @Override
    public String gameLost() {
        return null;
    }


}
