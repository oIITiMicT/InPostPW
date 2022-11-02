package com.example.InPostPW.builder.impl;

import com.example.InPostPW.builder.NewUserBuilder;
import com.example.InPostPW.dto.RegistrationFormDto;
import com.example.InPostPW.model.User;
import com.example.InPostPW.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@RequiredArgsConstructor
@Component
public class NewUserBuilderImpl implements NewUserBuilder {

    private final PasswordEncoder passwordEncoder;

    private final RoleService roleService;

    //TODO builder
    @Override
    public User createNewUser(RegistrationFormDto registrationForm) {
        User user = new User();
        user.setPassword(passwordEncoder.encode(registrationForm.getPassword()));
        user.setUsername(registrationForm.getUsername());
        user.setEmail(registrationForm.getEmail());
        user.setRole(roleService.findRoleByName("user").get());
        user.setExpectedPackages(new ArrayList<>());
        user.setSendedPackages(new ArrayList<>());
        return user;
    }
}
