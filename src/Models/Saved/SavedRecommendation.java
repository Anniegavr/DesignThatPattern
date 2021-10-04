package Models.Saved;


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
