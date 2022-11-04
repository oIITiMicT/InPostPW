package com.example.InPostPW.services.impl;

import com.example.InPostPW.model.Package;
import com.example.InPostPW.repository.PackageRepository;
import com.example.InPostPW.services.PackageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PackageServiceImpl implements PackageService {

    private final PackageRepository packageRepository;

    @Override
    public Optional<Package> findPackageById(Long id) {
        return packageRepository.findById(id);
    }
}
