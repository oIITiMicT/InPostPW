package com.example.InPostPW.dto;

import lombok.Data;

import java.util.List;

@Data
public class PackageResponseDto {
    private Long id;
    private String tracker;
    private String shippingAddress;
    private String destinationAddress;
    private String recipient;
    private String sender;
    private List<Long> stagesId;
}
