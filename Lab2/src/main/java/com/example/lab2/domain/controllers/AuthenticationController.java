package com.example.lab2.domain.controllers;

import com.example.lab2.domain.client.Client;
import com.example.lab2.domain.dao.ClientRepository;
import com.example.lab2.domain.dao.RoleRepository;
import com.example.lab2.domain.models.ERole;
import com.example.lab2.domain.models.RefreshToken;
import com.example.lab2.domain.models.Role;
import com.example.lab2.domain.payload.request.LoginRequest;
import com.example.lab2.domain.payload.request.SignupRequest;
import com.example.lab2.domain.payload.response.JwtResponse;
import com.example.lab2.domain.payload.response.MessageResponse;
import com.example.lab2.domain.security.jwt.JwtUtils;
import com.example.lab2.domain.security.jwt.payload.requests.TokenRefreshRequest;
import com.example.lab2.domain.security.jwt.payload.responses.TokenRefreshResponse;
import com.example.lab2.domain.services.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    private final PasswordEncoder encoder;

    private final RefreshTokenService refreshTokenService;

    private final RoleRepository roleRepository;

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    private final ClientRepository ownerRepository;


    /** Sign in request **/
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        OwnerDetailsImpl userDetails = (OwnerDetailsImpl) authentication.getPrincipal();

        String jwt = jwtUtils.generateJwtToken(userDetails);

        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

        return ResponseEntity.ok(
                new JwtResponse(jwt, refreshToken.getToken(), userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
    }

    /**
     *
     * @param request
     * @return
     */
    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getClient)
                .map(user -> {
                    String token = jwtUtils.generateTokenFromUsername(user.getUsername());
                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }

    /** Sign up request **/
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (ownerRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        Client user = new Client(signUpRequest.getUsername(),signUpRequest.getUsername(), encoder.encode(signUpRequest.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(ERole.USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);

        user.setRoles(roles);
        ownerRepository.save(user);
        return ResponseEntity.ok(user);
    }



}
