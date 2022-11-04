package com.example.InPostPW.controller;

import com.example.InPostPW.exception.PackageNotFoundException;
import com.example.InPostPW.model.Package;
import com.example.InPostPW.services.PackageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/package")
public class PackageController {

    private final PackageService packageService;

    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<?> getPackageById(@PathVariable Long id) {
        Package parcel = packageService.findPackageById(id).orElseThrow(() -> new PackageNotFoundException(id));
        return new ResponseEntity<>(parcel, HttpStatus.OK);
    }
}
