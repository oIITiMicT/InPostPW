package com.example.InPostPW.dto;

import lombok.Data;

@Data
public class NewPackageFormDto {
    private String shippingAddress;
    private String destinationAddress;
    private String recipient;
}
