package com.example.InPostPW.services;

import com.example.InPostPW.model.Package;
import com.example.InPostPW.model.Stage;

public interface StageService {

    void createInitStage(Package parcel);

    Stage saveStage(Stage stage);
}
