package com.example.InPostPW.controller;

import com.example.InPostPW.exception.PackageNotFoundException;
import com.example.InPostPW.services.PackageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.InPostPW.model.Package;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/package")
public class PackageController {

    private final PackageService packageService;

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getPackageById(@PathVariable Long id) {
        Package parcel = packageService.findPackageById(id).orElseThrow(PackageNotFoundException::new);
        return new ResponseEntity<>(parcel, HttpStatus.OK);
    }
}
