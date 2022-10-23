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
    @Value("${jwt.refresh.token.duration_in_ms}")
    private String refreshTokenDurationMS;

    @Value("${jwt.access.token.duration_in_ms}")
    private String accessTokenDurationMS;

    @Value("${jwt.token.secret}")
    private String secretKey;

    private final UserServiceImpl userService;

    @Override
    public String provide(String username) {
        UserDetails userDetails = userService.loadUserByUsername(username);
        Algorithm algorithm = Algorithm.HMAC256(secretKey.getBytes());
        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + Long.parseLong(refreshTokenDurationMS)))
                .withClaim("role", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
    }
}
