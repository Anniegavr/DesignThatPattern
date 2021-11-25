package com.example.lab2.domain.dao;

import com.example.lab2.domain.models.datasets.tv_series_dataset.tv_shows_data.TVShowsDataset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface TVSeriesRepository extends JpaRepository<TVShowsDataset, Long> {
    List<TVShowsDataset> findTVShowsDatasetByGenreContains(String genre);
}
