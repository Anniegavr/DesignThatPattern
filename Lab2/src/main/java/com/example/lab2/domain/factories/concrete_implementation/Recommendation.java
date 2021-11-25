package com.example.lab2.domain.factories.concrete_implementation;

import com.example.lab2.domain.factories.abstractions.IRecommFactory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity
@Getter
@Setter
public class Recommendation {
    @SequenceGenerator(
            name = "recommendation_sequence",
            sequenceName = "recommendation_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "recommendation_sequence"
    )
    @Id
    @Column(nullable = false)
    private Long recommendationId;

    public Recommendation(Long clientId, String clientUsername, String recommendation) {
        this.clientId = clientId;
        this.clientUsername = clientUsername;
        this.recommendation = recommendation;
    }

    private Long clientId;
    private String clientUsername;
    private String recommendation;

}
