package com.example.InPostPW.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
public class NewStageFormDto {
    private String description;
    private Long packageId;
    private Date time;
}
