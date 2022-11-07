package com.example.InPostPW.services;

import com.example.InPostPW.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.Optional;

public interface UserService extends UserDetailsService {

    Optional<User> findUserById(Long id);

    Optional<User> findUserByEmail(String email);

    User saveUser(User user);

    Optional<User> findUserByUsername(String username);
}
