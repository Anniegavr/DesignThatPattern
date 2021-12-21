package com.example.lab2.domain.statePattern;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Player {
    private State state;
    private boolean gameOn;
    private int lives;
    private List<Character> guessedLetters = new ArrayList<Character>();
    private String hangCurrentState;
    private String currentStateInformation;
    private String wordToBeGuessed;
    //  "\n +---+\n"+" |   |\n"+" O   |\n"+"/|\\  |\n"+"/ \\  |\n"+"     |\nTTTTTT"

    public void changeState(State state) {
        this.state = state;


    }

    public void addCorrectLetter(Character c){
        this.guessedLetters.add(c);
    }

}
