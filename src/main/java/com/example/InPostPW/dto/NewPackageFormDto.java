package com.example.InPostPW.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
public class NewPackageFormDto {
    private String shippingAddress;
    private String destinationAddress;
    private String recipient;
}
