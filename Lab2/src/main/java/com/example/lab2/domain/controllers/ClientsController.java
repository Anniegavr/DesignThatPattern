package com.example.lab2.domain.controllers;

import com.example.lab2.domain.client.Client;
import com.example.lab2.domain.dao.ClientRepository;
import com.example.lab2.domain.payload.response.SearchClientResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ClientsController {

    private final ClientRepository clientRepository;

    /** Get information about all users **/
    @GetMapping("/clients")
    public ResponseEntity<List<Client>> getAllUsers() {
        try {
            List<Client> users = new ArrayList<>(clientRepository.findAll());

            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    /**
     *  This function returns a list of all possible matches for the username being searched.
     *      * It comes in handy when one doesn't know the entire username of the sought user.
     *      * In return to the search request, this function returns a list of usernames (only).
     *      * If the one searching found the username they needed, they may proceed to use the /searchOwners/{username}
     *      * endpoint to get a full description of the specified user and its username.
     * @param clientUsername
     * @return
     */
    @GetMapping("/searchAllOwners/{username}")
    public ResponseEntity<?> getAllUsersByUsername(@PathVariable("username") String clientUsername) {
        try {
            List<Client> users = new ArrayList<>(clientRepository.findAllByUsernameContaining(clientUsername));

            List<SearchAllClientsRequest> saoList = new ArrayList<>();
            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            for (Client ow : users){
                SearchAllClientsRequest sao = new SearchAllCleintsRequest(ow.getClientId(), ow.getUsername());
                saoList.add(sao);
            }

            SearchClientResponse searchClientResponse = new SearchClientResponse();
            searchClientResponse.setUsersFound(saoList);
            return new ResponseEntity<>(searchClientResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


    /** Find user by their id **/
    @GetMapping("/clients/{clientId}")
    public ResponseEntity<Client> getUsersById(@PathVariable("clientId") long id) {
        Optional<Client> userData = clientRepository.findById(id);

        return userData.map(owner -> new ResponseEntity<>(owner, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /** This function returns a single user and all the information about them.
     * In order to find a specific user, one must know the correct username. **/
    @GetMapping("/searchClients/{username}")
    public ResponseEntity<Client> getUserByUsername(@PathVariable("username") String clientUsername) {
        Optional<Client> userData = clientRepository.findClientByUsername(clientUsername);

        return userData.map(owner -> new ResponseEntity<>(owner, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /** Delete all users **/
    @DeleteMapping("/clients")
    public ResponseEntity<HttpStatus> deleteAllUsers() {
        try {
            clientRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }

    }

    /** Delete user by their id **/
    @DeleteMapping("/clients/{clientId}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("clientId") long id) {
        try {
            clientRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
