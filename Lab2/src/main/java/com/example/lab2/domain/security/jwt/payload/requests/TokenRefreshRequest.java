package com.example.lab2.domain.security.jwt.payload.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class TokenRefreshRequest {
    @NotBlank
    private String refreshToken;

}
