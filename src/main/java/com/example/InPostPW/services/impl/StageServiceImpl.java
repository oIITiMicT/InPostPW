package com.example.InPostPW.services.impl;

import com.example.InPostPW.model.Package;
import com.example.InPostPW.model.Stage;
import com.example.InPostPW.repository.StageRepository;
import com.example.InPostPW.services.StageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@RequiredArgsConstructor
@Service
public class StageServiceImpl implements StageService {

    private static final String CREATE_MESSAGE = "Your parcel successfully created";

    private final StageRepository stageRepository;

    @Override
    public void createInitStage(Package parcel) {
        Stage stage = new Stage();
        stage.setParcel(parcel);
        stage.setTime(new Date());
        stage.setDescription(CREATE_MESSAGE);
        saveStage(stage);
    }

    @Override
    public Stage saveStage(Stage stage) {
        return stageRepository.save(stage);
    }
}
