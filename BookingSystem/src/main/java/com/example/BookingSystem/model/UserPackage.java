package com.example.BookingSystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class UserPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "package_id", nullable = false)
    private Package pkg; // Use a different variable name to avoid confusion

    @Column(nullable = false)
    private LocalDateTime purchaseDate; // When the package was purchased

    @Column(nullable = false)
    private int remainingCredits; // Remaining credit
}
