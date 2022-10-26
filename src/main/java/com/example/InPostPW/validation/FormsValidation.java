package com.example.InPostPW.validation;

import org.springframework.stereotype.Service;

import com.example.InPostPW.dto.RegistrationFormDto;
import com.example.InPostPW.exception.IllegalFormFieldException;

@Service
public class FormsValidation {
    //TODO Field name
    public void vaidateRegistrationForm(RegistrationFormDto registrationForm) {
        if (registrationForm.getEmail() == null) {
            throw new IllegalFormFieldException("email");
        }

        if (registrationForm.getPassword() == null) {
            throw new IllegalFormFieldException("password");
        }

        if (registrationForm.getUsername() == null) {
            throw new IllegalFormFieldException("username");
        }
    }
}
