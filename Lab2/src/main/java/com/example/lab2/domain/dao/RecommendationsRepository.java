package com.example.lab2.domain.dao;

import com.example.lab2.domain.factories.concrete_implementation.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface RecommendationsRepository extends JpaRepository<Recommendation, Long> {
    List<Recommendation> findRecommendationByRecommendationContaining(String recommendation);
}
