package com.example.lab2.domain.models.saved_recommendations_singletons;


public class SavedRecommendation {
    private static SavedRecommendation savedRecommendation;

    private SavedRecommendation(){}

    public static synchronized SavedRecommendation getSavedRecommendation(){
        if(savedRecommendation == null){
            savedRecommendation = new SavedRecommendation();
        }
        return savedRecommendation;
    }

    public static SavedRecommendation getInstanceUsingDoubleLocking(){
        if(savedRecommendation == null){
            synchronized (SavedRecommendation.class) {
                if(savedRecommendation == null){
                    savedRecommendation = new SavedRecommendation();
                }
            }
        }
        return savedRecommendation;
    }
}
