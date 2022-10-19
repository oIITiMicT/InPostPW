package com.example.InPostPW.controller;

import com.example.InPostPW.builder.UserResponseBuilder;
import com.example.InPostPW.dto.UserResponseDto;
import com.example.InPostPW.exception.UserNotFoundException;
import com.example.InPostPW.model.User;
import com.example.InPostPW.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    private final UserResponseBuilder userResponseBuilder;

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        User user = userService.findUserById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        return new ResponseEntity<>(userResponseBuilder.convertUserToUserResponse(user), HttpStatus.OK);
    }
}
