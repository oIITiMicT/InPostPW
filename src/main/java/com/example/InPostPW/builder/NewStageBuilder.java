package com.example.InPostPW.builder;

import com.example.InPostPW.dto.NewStageFormDto;
import com.example.InPostPW.model.Stage;

public interface NewStageBuilder {

    Stage createNewStage(NewStageFormDto stageFormDto);
}
