package com.example.InPostPW.services;

import com.example.InPostPW.model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findUserById(Long id);

    Optional<User> findUserByEmail(String email);
}
