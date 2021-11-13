package com.example.lab2.domain.factories.concrete_implementation.BodyActivity;

import com.example.lab2.domain.factories.abstractions.IRecommFactory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BodyActivityRecommFactory extends IRecommFactory {

    public IRecommFactory findRecommendation(int option) {
        IRecommFactory bodyRecommendation = null;
        switch (option) {
            case 0:
                bodyRecommendation = new WalkWithFriends();
                break;

            case 1:  //Multitask
                bodyRecommendation = new Running();
                break;

            case 3:  //Shorter activity
                bodyRecommendation = new Hiking();
                break;

            default:  //Longer activity
                System.out.println("Select an option for duration");
                break;
        }
        return bodyRecommendation;
    }

    @Override
    public String returnRecommendation() {
        return null;
    }
}
