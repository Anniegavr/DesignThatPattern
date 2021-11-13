package com.example.lab2;

import com.example.lab2.domain.client.Client;
import com.example.lab2.domain.factories.abstractions.IRecommFactory;
import com.example.lab2.domain.factories.concrete_implementation.BodyActivity.BodyActivityRecommFactory;
import com.example.lab2.domain.factories.concrete_implementation.Watch.WatchRecommFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class Lab2Application {

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
    public static void main(String[] args) {
        SpringApplication.run(Lab2Application.class, args);
        Client currentClient = Client.getClient();
        System.out.println(String.format("Creating a recommendation for {}.\n You're logged in as: {}",recommend().getClass(), currentClient));

    }

}
