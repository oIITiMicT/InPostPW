package com.example.InPostPW.controller;

import com.example.InPostPW.builder.NewUserBuilder;
import com.example.InPostPW.builder.ResponseBuilders;
import com.example.InPostPW.dto.RegistrationFormDto;
import com.example.InPostPW.exception.UserNotFoundException;
import com.example.InPostPW.model.User;
import com.example.InPostPW.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private static final String NO_USER_MESSAGE = "User not found";
    private final UserService userService;
    private final ResponseBuilders userResponseBuilder;
    private final NewUserBuilder userBuilder;

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        User user = userService.findUserById(id).orElseThrow(() -> new UserNotFoundException(NO_USER_MESSAGE));
        return new ResponseEntity<>(userResponseBuilder.convertUserToUserResponse(user), HttpStatus.OK);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        User user = userService.findUserByUsername(username).orElseThrow(() -> new UserNotFoundException(NO_USER_MESSAGE));
        return new ResponseEntity<>(userResponseBuilder.convertUserToUserResponse(user), HttpStatus.OK);
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        User user = userService.findUserByEmail(email).orElseThrow(() -> new UserNotFoundException(NO_USER_MESSAGE));
        return new ResponseEntity<>(userResponseBuilder.convertUserToUserResponse(user), HttpStatus.OK);
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationFormDto registrationForm) {
        User user = userBuilder.createNewUser(registrationForm);
        user = userService.saveUser(user);
        return new ResponseEntity<>(userResponseBuilder.convertUserToUserResponse(user), HttpStatus.OK);
    }

    @GetMapping("user/{id}/packages/sent")
    public ResponseEntity<?> getUserSentPackages(@PathVariable Long id) {
        User user = userService.findUserById(id).orElseThrow(() -> new UserNotFoundException(NO_USER_MESSAGE));
        return new ResponseEntity<>(user.getSentPackages(), HttpStatus.OK);
    }

    @GetMapping("user/{id}/packages/expected")
    public ResponseEntity<?> getUserExpectedPackages(@PathVariable Long id) {
        User user = userService.findUserById(id).orElseThrow(() -> new UserNotFoundException(NO_USER_MESSAGE));
        return new ResponseEntity<>(user.getExpectedPackages(), HttpStatus.OK);
    }
}
