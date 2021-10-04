import Client.Client;
import Factories.Abstractions.IRecommFactory;
import Factories.ConcreteImpl.BodyActivity.BodyActivityRecommFactory;
import Factories.ConcreteImpl.Watch.Anime;
import Factories.ConcreteImpl.Watch.WatchRecommFactory;

import java.util.Scanner;

public class Main {

    public enum RecommOptions {
        Shorter,
        Multitask,
        Longer
        //all options here
    }

    public enum TypeOfActivity{
        Body,
        Watch
    }



    public static void main(String[] args) {
        Client currentClient = Client.getClient();
        System.out.println(String.format("Creating a recommendation for {}.\n You're logged in as: {}",recommend().getClass(), currentClient));


    }

    private static IRecommFactory recommend() {
        Scanner scanner = new Scanner("Choose a type of activity:\n");
        TypeOfActivity op = TypeOfActivity.valueOf(scanner.toString());
        IRecommFactory irf = null;
        switch(op) {
            case Body:
                irf = new BodyActivityRecommFactory();
                break;
            case Watch:
                irf = new WatchRecommFactory();
                break;
            default:
                System.out.println("Select an option for duration");
                break;
        }
        return irf;
    }
}
