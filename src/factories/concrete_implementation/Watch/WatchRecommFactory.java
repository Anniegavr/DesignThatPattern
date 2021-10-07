package factories.concrete_implementation.Watch;

import factories.abstractions.IRecommFactory;

public class WatchRecommFactory extends IRecommFactory{

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
