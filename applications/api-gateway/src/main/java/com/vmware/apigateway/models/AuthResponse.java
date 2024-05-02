package com.vmware.apigateway.models;

import lombok.*;

import java.util.Collection;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

    private String userId;
    private String token;
    private String refreshToken;
    private long expiresAt;
    private Collection<String> authorities;

}
