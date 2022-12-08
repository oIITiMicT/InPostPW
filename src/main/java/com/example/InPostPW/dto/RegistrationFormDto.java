package com.example.InPostPW.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationFormDto {
    private String username;
    private String password;
    private String email;


}