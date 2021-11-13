package com.example.lab2.domain.dao;
import com.example.lab2.domain.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/** This repository persists and enables access to information about clients. It extends
 * JpaRepository and provides a finder method. **/
@Repository
@Transactional(readOnly = true)
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findById(Long clientId);

    Optional<Client> findClientByUsername(String clientUsername);

    Optional<Client> findClientByUsernameContaining(String clientUsername);

    List<Client> findAllByUsernameContaining(String ownerUsername);

    Boolean existsByUsername(String username);


}
