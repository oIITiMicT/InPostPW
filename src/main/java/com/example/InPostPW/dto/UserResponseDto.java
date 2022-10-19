package com.example.InPostPW.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserResponseDto {
    private Long id;

    private String username;

    private String email;

    private List<Long> sendedPackagesId;

    private List<Long> expectedPackagesId;
}
