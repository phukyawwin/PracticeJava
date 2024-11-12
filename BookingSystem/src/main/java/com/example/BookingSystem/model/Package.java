package com.example.BookingSystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name; // e.g., "Basic Package"

    @Column(nullable = false)
    private int credits; // Number of credits

    @Column(nullable = false)
    private double price; // Price of the package

    @Column(nullable = false)
    private String country; // Country where the package is available

    @Column(name = "expiration_date", nullable = false)
    private LocalDateTime expirationDate; // Expiration date of the package

}
