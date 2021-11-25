package com.example.lab2.domain.factories.concrete_implementation.Watch;

import com.example.lab2.domain.factories.abstractions.IWatchActivity;
import com.example.lab2.domain.security.services.TVSeriesService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity
public class TVSeries extends WatchRecommFactory implements IWatchActivity {
    @SequenceGenerator(
            name = "tv_series_sequence",
            sequenceName = "tv_series_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "tv_series_sequence"
    )
    @Id
    @Column(nullable = false)
    Long tvSeriesID;
    String name;
    String genres;

    public TVSeries(String name, String genres) {
        this.name = name;
        this.genres = genres;
    }

    @Override
    public List<TVSeries> returnRecommendation(){
        TVSeriesService tvSeriesService = new TVSeriesService();
        return tvSeriesService.findMatch();
    }


}
