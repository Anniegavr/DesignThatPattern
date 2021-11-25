package com.example.lab2.domain.factories.concrete_implementation.Watch;

import com.example.lab2.domain.factories.abstractions.IRecommFactory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
public class WatchRecommFactory extends IRecommFactory {

    public IRecommFactory findRecommendation(String option) {
        IRecommFactory watchRecommendation = null;
        switch (option) {
            case "1": //Multitask
                watchRecommendation = new TalkShow();
                break;

            case "2": //Shorter activity
                watchRecommendation = new Anime();
                break;

            case "3":  //Longer activity
                watchRecommendation = new TVSeries();
                break;

            default:
                System.out.println("Select an option for duration first.");
                break;
        }
        return watchRecommendation;
    }

    @Override
    public List<TVSeries> returnRecommendation() {
        return null;
    }
}
