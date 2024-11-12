package com.example.BookingSystem.service;

import com.example.BookingSystem.model.User;
import com.example.BookingSystem.model.Package;
import com.example.BookingSystem.model.UserPackage;
import com.example.BookingSystem.repository.PackageRepository;
import com.example.BookingSystem.repository.UserPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PackageService {
    @Autowired
    private PackageRepository packageRepository;

    @Autowired
    private UserPackageRepository userPackageRepository;

    public List<Package> getPackagesByCountry(String country) {
        return packageRepository.findByCountry(country);
    }

    public Package addPackage(Package pkg) {
        return packageRepository.save(pkg);
    }
    public void purchasePackage(User user, Package pkg) {
        UserPackage userPackage = new UserPackage();
        userPackage.setUser (user);
        userPackage.setPkg(pkg);
        userPackage.setPurchaseDate(LocalDateTime.now());
        userPackage.setRemainingCredits(pkg.getCredits());
        userPackageRepository.save(userPackage);
    }

    public List<UserPackage> getUserPackages(User user) {
        return userPackageRepository.findByUser (user);
    }
}
