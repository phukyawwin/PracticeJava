package com.example.BookingSystem.repository;

import com.example.BookingSystem.model.User;
import com.example.BookingSystem.model.UserPackage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserPackageRepository extends JpaRepository<UserPackage, Long> {
    List<UserPackage> findByUser (User user);

    List<UserPackage> findByUserId(Long userId);
}
