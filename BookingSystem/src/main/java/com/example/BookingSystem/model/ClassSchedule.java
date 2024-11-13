package com.example.BookingSystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class ClassSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name; // e.g., "Yoga Class"

    @Column(nullable = false)
    private String country; // Country where the class is available

    @Column(nullable = false)
    private int availableSlots; // Number of available slots

    @Column(nullable = false)
    private int requiredCredits; // Credits required to book the class

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime; // Start time of the class
}
