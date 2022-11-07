package com.example.InPostPW.controller;

import com.example.InPostPW.builder.NewStageBuilder;
import com.example.InPostPW.dto.NewStageFormDto;
import com.example.InPostPW.exception.StageNotFoundException;
import com.example.InPostPW.model.Stage;
import com.example.InPostPW.services.StageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class StageController {

    private final NewStageBuilder stageBuilder;

    private final StageService stageService;

    @PostMapping
    @RequestMapping("/stage/create")
    public ResponseEntity<?> createNewStage(@RequestBody NewStageFormDto stageFormDto) {
        Stage stage = stageBuilder.createNewStage(stageFormDto);
        stage = stageService.saveStage(stage);
        return new ResponseEntity<>(stage, HttpStatus.CREATED);
    }

    @GetMapping
    @RequestMapping("/stage/{id}")
    public ResponseEntity<?> getStageById(@PathVariable Long id) {
        Stage stage = stageService.findStageById(id).orElseThrow(() -> new StageNotFoundException(id));
        return new ResponseEntity<>(stage, HttpStatus.OK);
    }
}
