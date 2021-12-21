package com.example.lab2.domain.statePattern;

public class DownState extends State{

    DownState(Player player) {
        super(player);
    }

    @Override
    public String gameOn() {
        player.setLives(player.getLives()-1);
        if(player.getLives() == 0){
            gameLost();
            return "";
        }else if(player.getLives()==5){
            player.setHangCurrentState("\\n +---+\\n");
            player.setCurrentStateInformation("You have "+player.getLives()+" lives left\n"+player.getGuessedLetters()+player.getHangCurrentState());
            return player.getCurrentStateInformation();
        }else if(player.getLives()==4){
            player.setHangCurrentState("\n +---+\n |   |\n");
            player.setCurrentStateInformation("You have "+player.getLives()+" lives left\n"+player.getGuessedLetters()+player.getHangCurrentState());
            return player.getCurrentStateInformation();
        }else if(player.getLives()==3){
            player.setHangCurrentState("\n +---+\n |   |\n O   |\n");
            player.setCurrentStateInformation("You have "+player.getLives()+" lives left\n"+player.getGuessedLetters()+player.getHangCurrentState());
            return player.getCurrentStateInformation();
        }else if(player.getLives()==2){
            player.setHangCurrentState("\n +---+\n |   |\n O   |\n/|\\  |\n");
            player.setCurrentStateInformation("You have "+player.getLives()+" lives left\n"+player.getGuessedLetters()+player.getHangCurrentState());
            return player.getCurrentStateInformation();
        }else if(player.getLives()==1){
            player.setHangCurrentState("\n +---+\n |   |\n O   |\n/|\\  |\n/ \\  |\n");
            player.setCurrentStateInformation("You have "+player.getLives()+" lives left\n"+player.getGuessedLetters()+player.getHangCurrentState());
            return player.getCurrentStateInformation();
        }else{
            return "Error";
        }
    }

    @Override
    public String gameWon() {
        return null;
    }

    @Override
    public String gameLost() {
        player.setGameOn(false);
        player.setHangCurrentState("\n +---+\n |   |\n O   |\n/|\\  |\n/ \\  |\n     |\nTTTTTT");
        player.setCurrentStateInformation("Game Over! 🙁\n"+"\nYou have 0 lives left\n"+player.getHangCurrentState()+"\nThe word was: "+player.getWordToBeGuessed());
        return player.getCurrentStateInformation();
    }
}
