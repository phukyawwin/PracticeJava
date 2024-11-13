package com.example.BookingSystem.service;

import com.example.BookingSystem.exception.PackagePurchaseException;
import com.example.BookingSystem.model.User;
import com.example.BookingSystem.model.Package;
import com.example.BookingSystem.model.UserPackage;
import com.example.BookingSystem.repository.PackageRepository;
import com.example.BookingSystem.repository.UserPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
    public void  purchasePackage(User user, Package pkg) {
        List<UserPackage> existingPackages = userPackageRepository.findByUser (user);

        // Check if there is an existing package with the same country
        UserPackage existingUserPackage = existingPackages.stream()
                .filter(up -> up.getPkg().getCountry().equals(pkg.getCountry()))
                .findFirst()
                .orElse(null);

        if (existingUserPackage != null) {
            // If an existing package is found, update the remaining credits
            existingUserPackage.setRemainingCredits(existingUserPackage.getRemainingCredits() + pkg.getCredits());
            existingUserPackage.setPurchaseDate(LocalDateTime.now());
            userPackageRepository.save(existingUserPackage); // Update the existing package
        } else {
            // If no existing package is found, create a new one
            UserPackage newUserPackage = new UserPackage();
            newUserPackage.setUser (user);
            newUserPackage.setPkg(pkg);
            newUserPackage.setPurchaseDate(LocalDateTime.now());
            newUserPackage.setRemainingCredits(pkg.getCredits());
            userPackageRepository.save(newUserPackage); // Save the new package
        }
    }

    public List<UserPackage> getUserPackages(User user) {
        return userPackageRepository.findByUser (user);
    }
}
