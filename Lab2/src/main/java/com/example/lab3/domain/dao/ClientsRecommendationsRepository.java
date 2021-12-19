package com.example.lab2.domain.dao;

import com.example.lab2.domain.factories.abstractions.IRecommFactory;
import com.example.lab2.domain.factories.concrete_implementation.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface ClientsRecommendationsRepository extends JpaRepository<Recommendation, Long> {
    Optional<IRecommFactory> findRecommendationByRecommendationContaining(String obj);
}
