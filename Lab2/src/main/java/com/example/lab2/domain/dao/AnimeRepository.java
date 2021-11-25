package com.example.lab2.domain.dao;


import com.example.lab2.domain.models.datasets.anime_dataset.AnimeDataset.AnimeDataset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface AnimeRepository extends JpaRepository<AnimeDataset, Long> {
    List<AnimeDataset> findAnimeDatasetByGenreContains(String genre);
}
