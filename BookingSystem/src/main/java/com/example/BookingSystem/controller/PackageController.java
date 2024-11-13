package com.example.BookingSystem.controller;

import com.example.BookingSystem.model.User;
import com.example.BookingSystem.model.UserPackage;
import com.example.BookingSystem.repository.PackageRepository;
import com.example.BookingSystem.repository.UserPackageRepository;
import com.example.BookingSystem.repository.UserRepository;
import com.example.BookingSystem.service.GeoLocationService;
import com.example.BookingSystem.service.PackageService;
import com.example.BookingSystem.model.Package;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @Autowired
    private GeoLocationService geoLocationService;

    @GetMapping("/{country}")
    public List<Package> getPackages(@PathVariable String country) {
        return packageService.getPackagesByCountry(country);
    }

    @PostMapping("/purchase")
    public void purchasePackage( @RequestParam Long packageId) {
        // Fetch user and package by IDs
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        User user = userRepository.findById(currentUser.getId()).orElseThrow(() -> new RuntimeException("User  not found"));
        Package pkg = packageRepository.findById(packageId).orElseThrow(() -> new RuntimeException("Package not found"));
        packageService.purchasePackage(user, pkg);
    }

    @GetMapping("/getUserPackagesList")
    public List<UserPackage> getUserPackages() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        User user = userRepository.findById(currentUser.getId()).orElseThrow();
        return packageService.getUserPackages(user);
    }
    @PostMapping("/addNewPackage")
    public ResponseEntity<Package> addPackage(@RequestBody Package pkg) {
        Package createdPackage = packageService.addPackage(pkg);
        return ResponseEntity.ok(createdPackage);
    }

    @GetMapping("/getPackageByIp/{publicIp}")
    public ResponseEntity<List<Package>> getPackageByIp(@PathVariable String publicIp,HttpServletRequest request) {
        try {
            String country = geoLocationService.getCountryByIp(publicIp);

            return ResponseEntity.ok(packageService.getPackagesByCountry(country));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    private String getClientIp(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }
}
