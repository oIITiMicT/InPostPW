package com.example.InPostPW.builder;

import com.example.InPostPW.dto.UserResponseDto;
import com.example.InPostPW.model.User;

public interface UserResponseBuilder {
    UserResponseDto convertUserToUserResponse(User user);
}
