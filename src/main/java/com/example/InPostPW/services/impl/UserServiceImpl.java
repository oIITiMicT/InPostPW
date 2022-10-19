package com.example.InPostPW.services.impl;

import com.example.InPostPW.exception.UserNotFoundException;
import com.example.InPostPW.model.User;
import com.example.InPostPW.repository.UserRepository;
import com.example.InPostPW.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = findUserByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
        log.info("User found in DB: {}", user.getEmail());
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRole().getPermissions().forEach(permission -> {
            authorities.add(new SimpleGrantedAuthority(permission.getName()));
        });

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), authorities
        );
    }
}
