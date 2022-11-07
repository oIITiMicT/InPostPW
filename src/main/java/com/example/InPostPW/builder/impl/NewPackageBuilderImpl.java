package com.example.InPostPW.builder.impl;

import com.example.InPostPW.builder.NewPackageBuilder;
import com.example.InPostPW.dto.NewPackageFormDto;
import com.example.InPostPW.exception.UserNotFoundException;
import com.example.InPostPW.model.Package;
import com.example.InPostPW.model.User;
import com.example.InPostPW.services.PackageService;
import com.example.InPostPW.services.TokenService;
import com.example.InPostPW.services.UserService;
import com.example.InPostPW.validation.FormsValidation;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;


@RequiredArgsConstructor
@Component
public class NewPackageBuilderImpl implements NewPackageBuilder {

    private final PackageService packageService;
    private final TokenService tokenService;
    private final UserService userService;
    private final FormsValidation formsValidation;

    @Override
    public Package createNewPackage(NewPackageFormDto packageFormDto) throws JSONException {
        formsValidation.validateCreateNewPackageForm(packageFormDto);
        Package parcel = new Package();
        String tracker = packageService.generateTracker();
        HttpServletRequest request = tokenService.getCurrentRequest();
        String token = tokenService.getTokenFromRequestHeader(request);
        String email = tokenService.getSubjectFromToken(token);
        User sender = userService.findUserByEmail(email).orElseThrow(() -> new UserNotFoundException(email));

        User recipient = userService.findUserByUsername(packageFormDto.getRecipient()).orElseThrow(() -> new UserNotFoundException(packageFormDto.getRecipient()));
        parcel.setTracker(tracker);
        parcel.setSender(sender);
        parcel.setRecipient(recipient);
        parcel.setStages(new ArrayList<>());
        parcel.setDestinationAddress(packageFormDto.getDestinationAddress());
        parcel.setShippingAdress(packageFormDto.getShippingAddress());
        return parcel;
    }
}
