package com.example.InPostPW.builder.impl;

import com.example.InPostPW.builder.NewStageBuilder;
import com.example.InPostPW.dto.NewStageFormDto;
import com.example.InPostPW.model.Stage;
import com.example.InPostPW.services.PackageService;
import com.example.InPostPW.validation.FormsValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class NewStageBuilderImpl implements NewStageBuilder {

    private final FormsValidation formsValidation;
    private final PackageService packageService;

    @Override
    public Stage createNewStage(NewStageFormDto stageFormDto) {
        formsValidation.validateCreateNewStageForm(stageFormDto);
        Stage stage = new Stage();
        stage.setDescription(stageFormDto.getDescription());
        Date time = stageFormDto.getTime();
        stage.setTime(time);
        stage.setParcel(packageService.findPackageById(stageFormDto.getPackageId()).get());
        return stage;
    }
}
