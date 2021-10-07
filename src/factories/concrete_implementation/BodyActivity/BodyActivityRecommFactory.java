package factories.concrete_implementation.BodyActivity;

import factories.abstractions.IRecommFactory;

public class BodyActivityRecommFactory extends IRecommFactory{

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
