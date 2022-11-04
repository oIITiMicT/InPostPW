package com.example.InPostPW.builder.impl;

import com.example.InPostPW.builder.UserResponseBuilder;
import com.example.InPostPW.dto.UserResponseDto;
import com.example.InPostPW.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import com.example.InPostPW.model.Package;

@Component
public class UserResponseBuilderImpl implements UserResponseBuilder {

    @Override
    public UserResponseDto convertUserToUserResponse(User user) {
        UserResponseDto userResponse = new UserResponseDto();
        userResponse.setId(user.getId());
        userResponse.setEmail(user.getEmail());
        userResponse.setUsername(user.getUsername());
        List<Long> sendedPackages = new ArrayList<>();
        for (Package now : user.getSentPackages()) {
            sendedPackages.add(now.getId());
        }
        userResponse.setSendedPackagesId(sendedPackages);
        List<Long> expectedPackages = new ArrayList<>();
        for (Package now : user.getExpectedPackages()) {
            expectedPackages.add(now.getId());
        }
        userResponse.setExpectedPackagesId(expectedPackages);
        return userResponse;
    }
}
