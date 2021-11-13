package com.example.lab2.domain.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @SequenceGenerator(
            name = "client_sequence",
            sequenceName = "client_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "client_sequence"
    )
    @Id
    @Column(nullable = false)
    private Long ClientId;
    private String Username;

    private static Client client;


    public static synchronized Client getClient(){
        if(client == null){
            client = new Client();
        }
        return client;
    }

    public static Client getInstanceUsingDoubleLocking(){
        if(client == null){
            synchronized (Client.class) {
                if(client == null){
                    client = new Client();
                }
            }
        }
        return client;
    }
}
