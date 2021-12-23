package com.example.lab2.domain.statePattern;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameOn extends State {

    GameOn(Player player) {
        super(player);
        player.setGameOn(true);
        player.setLives(6);
    }

    @Override
    public String gameOn() {
        player.setCurrentStateInformation("You have "+player.getLives()+" lives left\n"+player.getGuessedLetters()+player.getHangCurrentState());
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            int tryNo = 0;
            String currentLetter = scanner.nextLine();
            if(currentLetter.equals(String.valueOf(player.getWordToBeGuessed().charAt(tryNo)))){
                player.addCorrectLetter(currentLetter);
                if(player.getWordToBeGuessed().length()==player.getGuessedLetters().size()){
                    WinState ws = new WinState(player);
                    player.changeState(new WinState(player));
                    return ws.gameWon();
                }else{
                    gameOn();
                }
            }else{
                DownState ds = new DownState(player);
                if(!ds.gameLost().equals("You lost!")){
                    gameOn();
                }
                return player.getCurrentStateInformation();
            }
        }
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
