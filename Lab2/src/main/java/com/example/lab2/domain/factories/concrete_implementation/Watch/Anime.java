package com.example.lab2.domain.factories.concrete_implementation.Watch;


import com.example.lab2.domain.factories.abstractions.IWatchActivity;
import com.example.lab2.domain.security.services.AnimeService;
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
public class Anime extends WatchRecommFactory implements IWatchActivity {
//    @SequenceGenerator(
//            name = "anime_sequence",
//            sequenceName = "anime_sequence",
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "anime_sequence"
//    )
    @Id
    @Column(nullable = false)
    Long animeId;
    String name;
    String genres;
    @Override
    public List<Anime> returnRecommendation(){
        AnimeService as = new AnimeService();
        return as.findMatch();
    }
}
