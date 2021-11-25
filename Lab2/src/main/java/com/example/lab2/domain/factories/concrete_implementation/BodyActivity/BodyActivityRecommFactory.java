package com.example.lab2.domain.factories.concrete_implementation.BodyActivity;

import com.example.lab2.domain.factories.abstractions.IRecommFactory;
import com.example.lab2.domain.factories.concrete_implementation.Watch.TVSeries;
import lombok.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@RestController
@RequestMapping("/api/body_activity")
@RequiredArgsConstructor
public class BodyActivityRecommFactory extends IRecommFactory {

    public IRecommFactory createRecommendationType(String option) {
        IRecommFactory bodyRecommendation = null;
        switch (option) {
            case "1": //Multitask
                bodyRecommendation = new WalkWithFriends();
                break;

            case "2":  //Shorter activity
                bodyRecommendation = new Running();
                break;

            case "3":  //Longer activity
                bodyRecommendation = new Hiking();
                break;

            default:
                System.out.println("Select an option for duration");
                break;
        }
        return bodyRecommendation;
    }

    @Override
    public List<TVSeries> returnRecommendation() {
        return null;
    }


}
