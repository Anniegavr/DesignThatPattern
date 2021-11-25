package com.example.lab2.domain.controllers;

import com.example.lab2.domain.payload.request.LoginRequest;
import com.example.lab2.domain.payload.request.SignupRequest;
import com.example.lab2.domain.payload.response.MessageResponse;
import com.example.lab2.domain.security.jwt.payload.requests.TokenRefreshRequest;
import com.example.lab2.domain.security.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/** The controller receives and handles a request after it was filtered by OncePerRequestFilter.

 > AuthController handles signup/login requests

 – /api/auth/signup
    >>check existing username/email
    >>create new User (as USER if there is no specified role)
    >>save User to database using UserRepository

 – /api/auth/signin
    >>authenticate { username, password }
    >>update SecurityContext using Authentication object
    >>generate JWT
    >>get UserDetails from Authentication object
    >>the response contains JWT and UserDetails data*/

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {
    private static final org.slf4j.Logger Logger= LoggerFactory.getLogger(AuthenticationController.class);

    private final AuthenticationService authenticationService;

    /** Sign in request **/
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        return ResponseEntity.ok(
                authenticationService.authenticateUser(loginRequest));
    }

    /**
     *
     * @param request containing information about the user and their token
     * @return a refresh token
     */
    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
        return authenticationService.refreshtoken(request);
    }

    /** Sign up request **/
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (authenticationService.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }
        authenticationService.registerUser(signUpRequest);
        return ResponseEntity.ok(signUpRequest.getUsername());
    }



}
