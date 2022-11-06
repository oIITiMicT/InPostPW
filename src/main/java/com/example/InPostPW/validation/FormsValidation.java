package com.example.InPostPW.validation;


import com.example.InPostPW.dto.NewPackageFormDto;
import com.example.InPostPW.dto.RegistrationFormDto;
import org.json.JSONException;

public interface FormsValidation {
    void validateRegistrationForm(RegistrationFormDto registrationForm);

    void validateCreateNewPackageForm(NewPackageFormDto newPackageFormDto) throws JSONException;
}
