package com.example.lab2.domain.factories.concrete_implementation.Watch;

import com.example.lab2.domain.factories.abstractions.IRecommFactory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WatchRecommFactory extends IRecommFactory {

    public IRecommFactory findRecommendation(int option) {
        IRecommFactory watchRecommendation = null;
        switch (option) {
            case 0: //Multitask
                watchRecommendation = new Talk_Show();
                break;

            case 1: //Shorter activity
                watchRecommendation = new Anime();
                break;

            case 2:  //Longer activity
                watchRecommendation = new TV_Series();
                break;

            default:
                System.out.println("Select an option for duration first.");
                break;
        }
        return watchRecommendation;
    }

    @Override
    public String returnRecommendation() {
        return null;
    }
}
