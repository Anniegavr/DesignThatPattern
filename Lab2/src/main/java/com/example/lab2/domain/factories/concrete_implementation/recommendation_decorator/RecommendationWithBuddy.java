package com.example.lab2.domain.factories.concrete_implementation.recommendation_decorator;


import com.example.lab2.domain.dao.RecommendationsRepository;
import com.example.lab2.domain.factories.concrete_implementation.Recommendation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Getter
@Setter
public class RecommendationWithBuddy {
    private final RecommendationsRepository recommendationsRepository;
    private final Recommendation recommendation;
    private String userWithSameInterests;

    public List<String> findInterestsBuddy(){
        List<Recommendation> getSimilars = new ArrayList<Recommendation>(recommendationsRepository.findRecommendationByRecommendationContaining(recommendation.getRecommendation()));
        List<String> getBuddyUsernames = new ArrayList<String>();
        for(Recommendation rec : getSimilars){
            getBuddyUsernames.add(rec.getClientUsername());
        }
        return getBuddyUsernames;
    }
}
