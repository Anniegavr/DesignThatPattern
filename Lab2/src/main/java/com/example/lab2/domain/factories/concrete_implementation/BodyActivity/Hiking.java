package com.example.lab2.domain.factories.concrete_implementation.BodyActivity;

import com.example.lab2.domain.factories.abstractions.IBodyActivity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String genres) {
        this.duration = genres;
    }

    @Override
    public String returnRecommendation(){
        //some function to return data from dataset
        return "Name: ...\nLevel of difficulty: ...";
    }
}
