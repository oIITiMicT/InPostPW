package com.example.InPostPW.controller;

import com.example.InPostPW.dto.NewPackageFormDto;
import com.example.InPostPW.exception.PackageNotFoundException;
import com.example.InPostPW.model.Package;
import com.example.InPostPW.services.PackageService;
import com.example.InPostPW.validation.FormsValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/package")
public class PackageController {

    private final PackageService packageService;

    private final FormsValidation formsValidation;

    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<?> getPackageById(@PathVariable Long id) {
        Package parcel = packageService.findPackageById(id).orElseThrow(() -> new PackageNotFoundException(id));
        return new ResponseEntity<>(parcel, HttpStatus.OK);
    }

    @PostMapping
    @RequestMapping("/create")
    public ResponseEntity<?> createNewPackage(@RequestBody NewPackageFormDto packageFormDto) {
        formsValidation.validateCreateNewPackageForm(packageFormDto);

        return null;
    }
}