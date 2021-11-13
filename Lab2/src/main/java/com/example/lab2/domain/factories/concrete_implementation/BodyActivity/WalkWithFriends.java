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
    public String returnRecommendation(){
        //some function to return data from dataset
        return "Name: ...\nLevel of difficulty: ...";
    }
}
