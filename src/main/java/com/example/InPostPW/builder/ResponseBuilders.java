package com.example.InPostPW.builder;

import com.example.InPostPW.dto.PackageResponseDto;
import com.example.InPostPW.dto.UserResponseDto;
import com.example.InPostPW.model.User;
import com.example.InPostPW.model.Package;

public interface ResponseBuilders {

    UserResponseDto convertUserToUserResponse(User user);

    PackageResponseDto convertPackageToPackageResponseDto(Package parcel);
}
