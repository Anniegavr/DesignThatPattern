package com.example.lab2.domain.factories.concrete_implementation.BodyActivity;

import com.example.lab2.domain.factories.abstractions.IBodyActivity;
import com.example.lab2.domain.factories.concrete_implementation.Watch.TVSeries;
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
public class WalkWithFriends extends BodyActivityRecommFactory implements IBodyActivity {
    @SequenceGenerator(
            name = "walk_sequence",
            sequenceName = "walk_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "walk_sequence"
    )
    @Id
    @Column(nullable = false)
    Long walkId;
    String name;
    String duration;

    @Override
    public List<TVSeries> returnRecommendation(){
        return "How about taking a long walk with your friends?\n" +
                "You can walk through the park or even go somewhere to enjoy a breath-taking sight together.";
    }
}
