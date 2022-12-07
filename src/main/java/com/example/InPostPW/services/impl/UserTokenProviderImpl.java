package com.example.InPostPW.services.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.InPostPW.services.UserTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserTokenProviderImpl implements UserTokenProvider<String> {

    private final UserServiceImpl userService;

    @Value("${jwt.refresh.token.duration_in_ms}")
    private String refreshTokenDurationMS;

    @Value("${jwt.access.token.duration_in_ms}")
    private String accessTokenDurationMS;

    @Value("${jwt.token.secret}")
    private String secretKey;


    @Override
    public Map<String, String> provide(String username) {
        UserDetails userDetails = userService.loadUserByUsername(username);
        Algorithm algorithm = Algorithm.HMAC256(secretKey.getBytes());
        String accessToken = JWT.create()
                .withSubject(userDetails.getUsername())
                .withExpiresAt(accessTokenExpiration())
                .withClaim("role", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);

        String refreshToken = JWT.create()
                .withSubject(userDetails.getUsername())
                .withExpiresAt(refreshTokenExpiration())
                .sign(algorithm);

        return Map.of("access_token", accessToken, "refresh_token", refreshToken);
    }

    private Date accessTokenExpiration() {
        return new Date(System.currentTimeMillis() + Integer.parseInt(accessTokenDurationMS));
    }

    private Date refreshTokenExpiration() {
        return new Date(System.currentTimeMillis() + Long.parseLong(refreshTokenDurationMS));
    }
}
