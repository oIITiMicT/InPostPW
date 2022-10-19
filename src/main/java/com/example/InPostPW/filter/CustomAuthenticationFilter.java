package com.example.InPostPW.filter;

import com.example.InPostPW.exception.UserNotFoundException;
import com.example.InPostPW.services.UserService;
import com.example.InPostPW.services.impl.UserTokenProviderImpl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final UserTokenProviderImpl tokenProvider;
    private final UserService userService;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager,
                                      UserTokenProviderImpl tokenProvider,
                                      UserService userService) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.userService = userService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = null;
        String password;
        Authentication authentication = null;
        try {
            JsonNode json = new ObjectMapper().readTree(request.getReader());
            if (!(json.hasNonNull("email") && json.hasNonNull("password"))) {
                throw new AuthenticationServiceException("No 'email' and 'password' fields found in request.");
            }

            username = json.get("email").asText();
            password = json.get("password").asText();
            log.debug("Trying to authenticate. Username is {}.", username);
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(username, password);
            authentication = authenticationManager.authenticate(authenticationToken);
        } catch (AuthenticationException e) {
            throw new AuthenticationServiceException(String.format("Error during authentication of %s", username), e);
        } catch (IOException e) {
            throw new AuthenticationServiceException("Error reading request body by ObjectMapper", e);
        }

        log.info("User {}: successfully authenticated", username);
        return authentication;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User user = (User)authResult.getPrincipal();
        Map<String, String> tokensPair = tokenProvider.provide(user.getUsername());
        String accessToken = tokensPair.get(UserTokenProviderImpl.ACCESS_TOKEN);
        String refreshToken = tokensPair.get(UserTokenProviderImpl.REFRESH_TOKEN);
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("access_token", accessToken);
        responseData.put("user", obtainResponseUserData(user.getUsername()));
        Cookie cookie = new Cookie("refresh_token", refreshToken);
        response.addCookie(cookie);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        log.info("User {}: successfully authorized", user.getUsername());
        new ObjectMapper().writeValue(response.getOutputStream(), responseData);
    }

    private Map<String, Object> obtainResponseUserData(String username) {
        com.example.InPostPW.model.User user = userService.findUserByEmail(username).orElseThrow(() -> new UserNotFoundException(username));
        Map<String, Object> result = new HashMap<>();
        result.put("username", user.getUsername());
        result.put("roles", new String[] { user.getRole().getName() });
        result.put("2fa", false);
        return result;
    }
}
