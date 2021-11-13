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
    public String returnRecommendation(){
        //some function to return data from dataset
        return "Name: ...\nLevel of difficulty: ...";
    }
}
