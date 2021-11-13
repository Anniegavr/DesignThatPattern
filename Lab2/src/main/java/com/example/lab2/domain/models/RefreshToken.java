package com.example.lab2.domain.models;

import com.example.lab2.domain.client.Client;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.security.acl.Owner;
import java.time.Instant;

@Getter
@Setter
@Entity(name = "refreshtoken")
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ownerId;

    @OneToOne
    @JoinColumn(name = "clientId", referencedColumnName = "clientId")
    private Client client;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private Instant expiryDate;


}
