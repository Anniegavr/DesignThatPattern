package Factories.ConcreteImpl.Watch;

import Factories.Abstractions.IRecommFactory;

public class WatchRecommFactory extends IRecommFactory{

    public IRecommFactory findRecommendation(int option) {
        IRecommFactory watchRecommendation = null;
        switch (option) {
            case 0:
                watchRecommendation = new Talk_Show();
                break;

            case 1:
                watchRecommendation = new Anime();
                break;

            case 2:
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

//    @Override
//    public abstract String returnRecommendation();
}
