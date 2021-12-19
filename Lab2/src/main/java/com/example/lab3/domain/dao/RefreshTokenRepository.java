package com.example.lab2.domain.dao;

import com.example.lab2.domain.client.Client;
import com.example.lab2.domain.models.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByClientId(Long id);

    Optional<RefreshToken> findByToken(String token);

    int deleteByClient(Client client);
}
