package com.example.InPostPW.services.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.InPostPW.services.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserTokenProviderImpl implements TokenProvider<String> {

    public static final String ACCESS_TOKEN = "access_token";
    public static final String REFRESH_TOKEN = "refresh_token";

    private final UserServiceImpl userService;

    @Value("${jwt.refresh.token.duration_in_ms}")
    private String refreshTokenDurationMS;

    @Value("${jwt.access.token.duration_in_ms}")
    private String accessTokenDurationMS;

    @Value("${jwt.token.secret}")
    private String secretKey;


    @Override
    public Map<String, String> provide(String email) {
        UserDetails user = userService.loadUserByUsername(email);
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        String accessToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(accessTokenExpiration())
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);

        String refreshToken = JWT.create()
                .withSubject(user.getUsername())
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
