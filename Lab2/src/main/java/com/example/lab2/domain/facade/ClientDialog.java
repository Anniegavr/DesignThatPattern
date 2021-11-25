package com.example.lab2.domain.facade;

import com.example.lab2.domain.client.Client;
import com.example.lab2.domain.dao.RecommendationsRepository;
import com.example.lab2.domain.factories.abstractions.IRecommFactory;
import com.example.lab2.domain.factories.concrete_implementation.BodyActivity.BodyActivityRecommFactory;
import com.example.lab2.domain.factories.concrete_implementation.Recommendation;
import com.example.lab2.domain.factories.concrete_implementation.Watch.WatchRecommFactory;
import com.example.lab2.domain.security.services.ClientDetailsServiceImpl;
import com.example.lab2.domain.security.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class ClientDialog {
    private final ClientDetailsServiceImpl clientDetailsService;
    private final ClientService clientService;
    private final RecommendationsRepository recommendationsRepository;

    public void recommend(){
        IRecommFactory iRecommFactory;
        String recommendationAnswer = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose a type of activity:" +
                "\n1.Activity involving the body\n2.Watch something");
        String requestRecommendation = sc.nextLine();
        System.out.println("Ok, now choose an option from the ones below:\n");
        System.out.println("1.An activity where I can multitask\n2.Shorter activity\n3.Longer activity");
        String choice = sc.nextLine();
        if(Objects.equals(requestRecommendation, "1")){
            iRecommFactory = new BodyActivityRecommFactory().createRecommendationType(choice);
            recommendationAnswer = iRecommFactory.returnRecommendation();
            System.out.println(recommendationAnswer);
        }
        else if(Objects.equals(requestRecommendation, "2")){
            iRecommFactory = new WatchRecommFactory().findRecommendation(choice);
            recommendationAnswer = iRecommFactory.returnRecommendation();
            System.out.println(recommendationAnswer);
        }
        else{
            System.out.println("You must choose and type the number of your choice, then hit ENTER");
            recommend();
        }
        System.out.println("Would you like to keep this recommendation in your history?\nyes/no:");
        String ans = sc.nextLine();
        if(Objects.equals(ans, "yes")){
            Long clientId = clientDetailsService.getUserIdFromToken();
            Optional<Client> findClient = clientService.getUsersById(clientId);
            if(findClient.isPresent()){
                String clientUsername = findClient.get().getUsername();
                Recommendation savingRecommendation = new Recommendation(clientId, clientUsername, recommendationAnswer);
                recommendationsRepository.save(savingRecommendation);
            }
        } else {
            System.out.println("Recommendation discarded\n");
        }
    }
}
