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
public class Hiking extends BodyActivityRecommFactory implements IBodyActivity {
    @SequenceGenerator(
            name = "hike_sequence",
            sequenceName = "hike_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "hike_sequence"
    )
    @Id
    @Column(nullable = false)
    Long hikingId;
    String name;
    String duration;

    @Override
    public List<TVSeries> returnRecommendation(){
        return "How about a hike along a series of touristic destinations in your region?\nOr a hike through the closest forest.";
    }
}
