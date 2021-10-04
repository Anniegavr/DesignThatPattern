package Factories.ConcreteImpl.BodyActivity;

import Factories.Abstractions.IRecommFactory;

public class BodyActivityRecommFactory extends IRecommFactory{

    public IRecommFactory findRecommendation(int option) {
        IRecommFactory bodyRecommendation = null;
        switch (option) {
            case 0:
                bodyRecommendation = new WalkWithFriends();
                break;

            case 1:
                bodyRecommendation = new Running();
                break;

            case 3:
                bodyRecommendation = new Hiking();
                break;

            default:
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
