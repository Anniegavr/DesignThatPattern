package com.example.lab2.domain.statePattern;

public class DownState extends State{

    DownState(Player player) {
        super(player);
    }

    @Override
    public String gameOn() {
        player.setLives(player.getLives()-1);
        System.out.println("Lives: "+player.getLives()+"\n");
        if(player.getLives() == 0){
            return gameLost();
        }else if(player.getLives()==5){
            player.setHangCurrentState("\\n +---+\\n");
            player.setCurrentStateInformation("You have "+player.getLives()+" lives left\n"+player.getHangCurrentState());
            System.out.println(player.getCurrentStateInformation());
            return player.getCurrentStateInformation();
        }else if(player.getLives()==4){
            player.setHangCurrentState("\n +---+\n |   |\n");
            player.setCurrentStateInformation("You have "+player.getLives()+" lives left\n"+player.getHangCurrentState());
            System.out.println(player.getCurrentStateInformation());
            return player.getCurrentStateInformation();
        }else if(player.getLives()==3){
            player.setHangCurrentState("\n +---+\n |   |\n O   |\n");
            player.setCurrentStateInformation("You have "+player.getLives()+" lives left\n"+player.getHangCurrentState());
            System.out.println(player.getCurrentStateInformation());
            return player.getCurrentStateInformation();
        }else if(player.getLives()==2){
            player.setHangCurrentState("\n +---+\n |   |\n O   |\n/|\\  |\n");
            player.setCurrentStateInformation("You have "+player.getLives()+" lives left\n"+player.getHangCurrentState());
            System.out.println(player.getCurrentStateInformation());
            return player.getCurrentStateInformation();
        }else if(player.getLives()==1){
            player.setHangCurrentState("\n +---+\n |   |\n O   |\n/|\\  |\n/ \\  |\n");
            player.setCurrentStateInformation("You have "+player.getLives()+" lives left\n"+player.getHangCurrentState());
            System.out.println(player.getCurrentStateInformation());
            return player.getCurrentStateInformation();
        }else{
            System.out.println("Error");
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
        player.setCurrentStateInformation(LostState(Player player){
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
    });
        return player.getCurrentStateInformation();
    }
}
