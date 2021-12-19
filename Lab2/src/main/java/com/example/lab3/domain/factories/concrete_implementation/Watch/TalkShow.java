package com.example.lab2.domain.factories.concrete_implementation.Watch;

import com.example.lab2.domain.factories.abstractions.IWatchActivity;
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
public class TalkShow extends WatchRecommFactory implements IWatchActivity {
    @SequenceGenerator(
            name = "client_sequence",
            sequenceName = "client_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "client_sequence"
    )
    @Id
    @Column(nullable = false)
    Long talkId;
    String name;
    String genres;
    @Override
    public List<TVSeries> returnRecommendation(){
        //some function to return data from dataset
        return "Name: ...\nGenres: ...";
    }


}
