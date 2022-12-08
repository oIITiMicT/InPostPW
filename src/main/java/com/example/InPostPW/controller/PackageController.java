package com.example.InPostPW.controller;

import com.example.InPostPW.builder.NewPackageBuilder;
import com.example.InPostPW.builder.ResponseBuilders;
import com.example.InPostPW.dto.NewPackageFormDto;
import com.example.InPostPW.exception.PackageNotFoundException;
import com.example.InPostPW.model.Package;
import com.example.InPostPW.services.PackageService;
import com.example.InPostPW.services.StageService;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/package")
public class PackageController {

    private final PackageService packageService;
    private final NewPackageBuilder packageBuilder;
    private final ResponseBuilders responseBuilders;
    private final StageService stageService;

    @GetMapping
    @RequestMapping("/list")
    public ResponseEntity<?> getListOfPackages() {
        return new ResponseEntity<>(packageService.getListOfPackages(), HttpStatus.OK);
    }


    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<?> getPackageById(@PathVariable Long id) {
        Package parcel = packageService.findPackageById(id).orElseThrow(() -> new PackageNotFoundException(id));
        return new ResponseEntity<>(responseBuilders.convertPackageToPackageResponseDto(parcel), HttpStatus.OK);
    }

    @PostMapping
    @RequestMapping("/create")
    public ResponseEntity<?> createNewPackage(@RequestBody NewPackageFormDto packageFormDto) throws JSONException {
        Package parcel = packageBuilder.createNewPackage(packageFormDto);
        parcel = packageService.savePackage(parcel);
        stageService.createInitStage(parcel);
        return new ResponseEntity<>(responseBuilders.convertPackageToPackageResponseDto(parcel), HttpStatus.CREATED);
    }
}
