package com.example.InPostPW.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class NewStageFormDto {
    private String description;
    private Long packageId;
    private LocalDateTime time;
}
