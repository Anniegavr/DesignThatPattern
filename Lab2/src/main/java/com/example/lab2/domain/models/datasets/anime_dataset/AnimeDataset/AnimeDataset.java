package com.example.lab2.domain.models.datasets.anime_dataset.AnimeDataset;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
public class AnimeDataset {
    @Id
    private Long animeId;
    private String name;
    private String genre;
    private String type;
    private int episodes;
    private int rating;
    private int members;
}
