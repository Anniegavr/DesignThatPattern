package Factories.ConcreteImpl.Watch;

import Factories.Abstractions.IRecommFactory;
import Factories.Abstractions.IWatchActivity;

import java.util.List;

public class Talk_Show extends WatchRecommFactory implements IWatchActivity {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    String name;
    String genres;
    @Override
    public String returnRecommendation(){
        //some function to return data from dataset
        return "Name: ...\nGenres: ...";
    }


}
