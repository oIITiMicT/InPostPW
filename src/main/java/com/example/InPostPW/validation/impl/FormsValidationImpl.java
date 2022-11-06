package com.example.InPostPW.validation.impl;

import com.example.InPostPW.dto.NewPackageFormDto;
import com.example.InPostPW.dto.RegistrationFormDto;
import com.example.InPostPW.exception.IllegalFormFieldException;
import com.example.InPostPW.exception.UserNotFoundException;
import com.example.InPostPW.model.User;
import com.example.InPostPW.services.TokenService;
import com.example.InPostPW.services.UserService;
import com.example.InPostPW.validation.FormsValidation;
import com.example.InPostPW.validation.PasswordValidation;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@RequiredArgsConstructor
public class FormsValidationImpl implements FormsValidation {

    private static final String DUPLICATE_USERNAME_MESSAGE = "user with this username exist";
    private static final String DUPLICATE_EMAIL_MESSAGE = "user with this email exist";

    private final PasswordValidation passwordValidation;
    private final TokenService tokenService;

    private final UserService userService;

    //TODO Field name
    @Override
    public void validateRegistrationForm(RegistrationFormDto registrationForm) {
        final String username = registrationForm.getUsername();
        final String email = registrationForm.getEmail();
        final String password = registrationForm.getPassword();

        if (email == null) {
            throw new IllegalFormFieldException("email");
        }

        if (password == null) {
            throw new IllegalFormFieldException("password");
        }

        if (username == null) {
            throw new IllegalFormFieldException("username");
        }

        if (userService.findUserByUsername(username).isPresent()) {
            throw new IllegalFormFieldException(DUPLICATE_USERNAME_MESSAGE, username);
        }

        if (userService.findUserByEmail(email).isPresent()) {
            throw new IllegalFormFieldException(DUPLICATE_EMAIL_MESSAGE, email);
        }

        passwordValidation.checkPasswordValidity(registrationForm.getPassword());
    }

    @Override
    public void validateCreateNewPackageForm(NewPackageFormDto newPackageFormDto) throws JSONException {
        final String recipient = newPackageFormDto.getRecipient();
        final String shippingAddress = newPackageFormDto.getShippingAddress();
        final String destinationAddress = newPackageFormDto.getDestinationAddress();
        HttpServletRequest request = tokenService.getCurrentRequest();
        String token = tokenService.getTokenFromRequestHeader(request);
        String email = tokenService.getSubjectFromToken(token);
        User sender = userService.findUserByEmail(email).orElseThrow(() -> new UserNotFoundException(email));

        if (shippingAddress == null) {
            throw new IllegalFormFieldException("shipping address");
        }

        if (destinationAddress == null) {
            throw new IllegalFormFieldException("destination address");
        }

        if (recipient == null) {
            throw new IllegalFormFieldException("recipient");
        }

        if (userService.findUserByUsername(recipient).isEmpty()) {
            throw new UserNotFoundException(recipient);
        }

        if (recipient.equals(sender.getUsername())) {
            throw new IllegalFormFieldException("cannot send package for yourself", recipient);
        }
    }
}
