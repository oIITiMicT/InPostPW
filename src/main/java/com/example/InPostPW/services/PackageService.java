package com.example.InPostPW.services;

import com.example.InPostPW.model.Package;

import java.util.Optional;

public interface PackageService {

    Optional<Package> findPackageById(Long id);
}
