package com.example.InPostPW.validation;


import com.example.InPostPW.dto.NewPackageFormDto;
import com.example.InPostPW.dto.RegistrationFormDto;

public interface FormsValidation {
    void validateRegistrationForm(RegistrationFormDto registrationForm);

    void validateCreateNewPackageForm(NewPackageFormDto newPackageFormDto);
}
