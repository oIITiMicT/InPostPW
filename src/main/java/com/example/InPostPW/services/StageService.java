package com.example.InPostPW.services;

import com.example.InPostPW.model.Package;
import com.example.InPostPW.model.Stage;

import java.util.List;
import java.util.Optional;

public interface StageService {

    void createInitStage(Package parcel);

    Stage saveStage(Stage stage);

    Optional<Stage> findStageById(Long id);

    List<Stage> getStagesOfParcel(Long id);
}
