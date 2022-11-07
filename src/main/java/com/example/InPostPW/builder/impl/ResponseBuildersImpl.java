package com.example.InPostPW.builder.impl;

import com.example.InPostPW.builder.ResponseBuilders;
import com.example.InPostPW.dto.PackageResponseDto;
import com.example.InPostPW.dto.UserResponseDto;
import com.example.InPostPW.model.Stage;
import com.example.InPostPW.model.User;
import com.example.InPostPW.model.Package;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class ResponseBuildersImpl implements ResponseBuilders {

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

    @Override
    public PackageResponseDto convertPackageToPackageResponseDto(Package parcel) {
        PackageResponseDto packageResponseDto = new PackageResponseDto();
        packageResponseDto.setId(parcel.getId());
        packageResponseDto.setTracker(parcel.getTracker());
        packageResponseDto.setShippingAddress(parcel.getShippingAdress());
        packageResponseDto.setDestinationAddress(parcel.getDestinationAddress());
        packageResponseDto.setRecipient(parcel.getRecipient().getUsername());
        packageResponseDto.setSender(parcel.getSender().getUsername());
        List<Long> stagesId = new ArrayList<>();
        for (Stage stage : parcel.getStages()) {
            stagesId.add(stage.getId());
        }
        packageResponseDto.setStagesId(stagesId);
        return packageResponseDto;
    }
}
