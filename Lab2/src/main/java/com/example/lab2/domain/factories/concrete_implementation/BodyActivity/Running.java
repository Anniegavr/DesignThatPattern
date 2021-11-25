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
public class Running extends BodyActivityRecommFactory implements IBodyActivity {
    @SequenceGenerator(
            name = "running_sequence",
            sequenceName = "running_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "running_sequence"
    )
    @Id
    @Column(nullable = false)
    Long runningId;
    String name;
    String duration;

    @Override
    public List<TVSeries> returnRecommendation(){
        return "How about going for a run? To make it even more interesting listen to music or even to a podcast while" +
                "getting your muscle in shape.";
    }
}
