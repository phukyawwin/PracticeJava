package com.example.BookingSystem.controller;

import com.example.BookingSystem.model.User;
import com.example.BookingSystem.model.UserPackage;
import com.example.BookingSystem.repository.PackageRepository;
import com.example.BookingSystem.repository.UserPackageRepository;
import com.example.BookingSystem.repository.UserRepository;
import com.example.BookingSystem.service.PackageService;
import com.example.BookingSystem.model.Package;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/packages")
public class PackageController {
    @Autowired
    private PackageService packageService;

    @Autowired
    private PackageRepository packageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserPackageRepository userPackageRepository;


    @GetMapping("/{country}")
    public List<Package> getPackages(@PathVariable String country) {
        return packageService.getPackagesByCountry(country);
    }

    @PostMapping("/purchase")
    public void purchasePackage(@RequestParam Long userId, @RequestParam Long packageId) {
        // Fetch user and package by IDs
        User user = userRepository.findById(userId).orElseThrow();
        Package pkg = packageRepository.findById(packageId).orElseThrow();
        packageService.purchasePackage(user, pkg);
    }

    @GetMapping("/user/{userId}")
    public List<UserPackage> getUserPackages(@PathVariable Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return packageService.getUserPackages(user);
    }
    @PostMapping("/add")
    public ResponseEntity<Package> addPackage(@RequestBody Package pkg) {
        Package createdPackage = packageService.addPackage(pkg);
        return ResponseEntity.ok(createdPackage);
    }
}
