package com.example.InPostPW.services.impl;

import com.example.InPostPW.model.Package;
import com.example.InPostPW.repository.PackageRepository;
import com.example.InPostPW.services.PackageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class PackageServiceImpl implements PackageService {

    private final static String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789";
    private final static int TRACKER_LENGHT = 8;

    private final PackageRepository packageRepository;

    @Override
    public Optional<Package> findPackageById(Long id) {
        return packageRepository.findById(id);
    }

    @Override
    public String generateTracker() {
        Random random = new Random(System.nanoTime());
        StringBuilder tracker = new StringBuilder();
        for (int i = 0; i < TRACKER_LENGHT; i++) {
            int index = (int)(CHARACTERS.length() * random.nextDouble());
            tracker.append(CHARACTERS.charAt(index));
        }
        return tracker.toString();
    }

    @Override
    public Package savePackage(Package parcel) {
        return packageRepository.save(parcel);
    }

    @Override
    public List<Package> getListOfPackages() {
        return packageRepository.findAll();
    }


}
