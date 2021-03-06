package com.example.lab2.domain.controllers;

import com.example.lab2.domain.client.Client;
import com.example.lab2.domain.payload.response.SearchClientResponse;
import com.example.lab2.domain.security.jwt.AuthEntryPointJwt;
import com.example.lab2.domain.security.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ClientsController {
    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    private final ClientService clientService;

    /**
     * @return a list containing objects of type Owner to display all registered users
     */
    @GetMapping("/owners")
    public ResponseEntity<?> getAllUsers() {
        try {
            List<Client> users = clientService.getAllUsers();
            if (users.isEmpty()) {
                return ResponseEntity.status(204).body("No registered users");
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Couldn't retrieve information about all registered users");
            return ResponseEntity.status(204).body("An unexpected error occurred");
        }
    }

    /**
     *  This function returns a list of all possible matches for the username being searched.
     *      * It comes in handy when one doesn't know the entire username of the sought user.
     *      * In return to the search request, this function returns a list of usernames (only).
     *      * If the one searching found the username they needed, they may proceed to use the /searchOwners/{username}
     *      * endpoint to get a full description of the specified user and its username.
     *
     * @param ownerUsername - the searched user's possible username or a slice of it
     * @return a ResponseEntity containing info about the user being searched, when found
     */
    @GetMapping("/searchAllOwners/{username}")
    public ResponseEntity<?> getAllUsersByUsername(@PathVariable("username") String ownerUsername) {
        try {
            SearchClientResponse users = clientService.getAllUsersByUsername(ownerUsername);
            if (users.getUsersFound().isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Couldn't search all users matching the search' username");
            return ResponseEntity.status(204).body("An unexpected error occurred");
        }
    }


    /**
     * @param id of the owner being searched
     * @return a ResponseEntity containing an object of type Owner with info about the user being searched, when found
     */
    @GetMapping("/owners/{ownerId}")
    public ResponseEntity<Client> getUsersById(@PathVariable("ownerId") long id) {
        return clientService.getUsersById(id).map(owner -> new ResponseEntity<>(owner, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Search user by their username. In order to find a specific user, one must know the correct username.
     * @param clientUsername - the username by which the search is performed
     * @return a single user and all the information about them.
     */
    @GetMapping("/searchOwners/{username}")
    public ResponseEntity<Client> getUserByUsername(@PathVariable("username") String clientUsername) {
        return clientService.getUserByUsername(clientUsername).map(owner -> new ResponseEntity<>(owner, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Request to clear the entire list of registered users
     */
    @DeleteMapping("/owners")
    public void deleteAllUsers() {
        clientService.deleteAllUsers();
    }

    /**
     * Function to remove a user
     * @param id of the user
     * @return a ResponseEntity informing about the delete request status
     */
    @DeleteMapping("/owners/{ownerId}")
    public ResponseEntity<?> deleteUser(@PathVariable("ownerId") long id) {
        try {
            clientService.deleteUser(id);
            return ResponseEntity.status(200).body("Owner removed");
        } catch (Exception e) {
            logger.error("Couldn't delete user with id " + id);
            return ResponseEntity.status(417).body("An unexpected error occurred");
        }
    }
}
