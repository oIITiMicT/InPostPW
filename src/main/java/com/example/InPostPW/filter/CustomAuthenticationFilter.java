package com.example.InPostPW.filter;

import com.example.InPostPW.exception.UserNotFoundException;
import com.example.InPostPW.model.User;
import com.example.InPostPW.services.UserService;
import com.example.InPostPW.services.UserTokenProvider;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final String NO_USER_MESSAGE = "User not found";

    private final AuthenticationManager authenticationManager;

    private final UserTokenProvider userTokenProvider;

    private final UserService userService;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String email = null;
        String password;
        String salt = "";
        Authentication authentication = null;

        try {
            JsonNode json = new ObjectMapper().readTree(request.getReader());
            if (!(json.hasNonNull("email") && json.hasNonNull("password"))) {
                throw new AuthenticationServiceException("Invalid request. Expected JSON with 'email' and 'password' fields.");
            }
            email = json.get("email").asText();
            password = json.get("password").asText();
            Optional<User> user = userService.findUserByEmail(email);
            if (user.isPresent()) {
                salt = user.get().getSalt();
            }
            password = salt + password;
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(email, password);
            authentication = authenticationManager.authenticate(authenticationToken);
        } catch (AuthenticationException e) {
            throw new AuthenticationServiceException(String.format("Error during authentication of %s", email), e);
        } catch (IOException e) {
            throw new AuthenticationServiceException("Error reading request body by ObjectMapper", e);
        }
        return authentication;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        UserDetails user = (UserDetails)authResult.getPrincipal();
        String token = userTokenProvider.provide(user.getUsername());
        response.setHeader("token", token);
        response.addHeader("Vary", "Access-Control-Expose-Headers");
        response.setHeader("Access-Control-Expose-Headers", "token");
    }
}