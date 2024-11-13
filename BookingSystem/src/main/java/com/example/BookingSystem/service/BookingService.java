package com.example.BookingSystem.service;

import com.example.BookingSystem.model.Booking;
import com.example.BookingSystem.model.ClassSchedule;
import com.example.BookingSystem.model.User;
import com.example.BookingSystem.model.UserPackage;
import com.example.BookingSystem.repository.BookingRepository;
import com.example.BookingSystem.repository.ClassScheduleRepository;
import com.example.BookingSystem.repository.UserPackageRepository;
import com.example.BookingSystem.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ClassScheduleRepository classScheduleRepository;

    @Autowired
    private UserPackageRepository userPackageRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Booking bookClass(Long userId, Long classId) {
        ClassSchedule classSchedule = classScheduleRepository.findById(classId)
                .orElseThrow(() -> new RuntimeException("Class not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User  not found"));

        // Find the user's package that corresponds to the class's country
        UserPackage userPackage = userPackageRepository.findByUserAndPkg_Country(user, classSchedule.getCountry())
                .orElseThrow(() -> new RuntimeException("No package available for this country"));

        // Check if the user has enough credits
        if (userPackage.getRemainingCredits() < classSchedule.getRequiredCredits()) {
            throw new RuntimeException("Not enough credits to book this class");
        }

        // Proceed with booking
        if (classSchedule.getAvailableSlots() > 0) {
            Booking booking = new Booking();
            booking.setUser (user);
            booking.setClassSchedule(classSchedule);
            booking.setCancelled(false);
            booking.setBookingTime(LocalDateTime.now());

            // Deduct credits
            userPackage.setRemainingCredits(userPackage.getRemainingCredits() - classSchedule.getRequiredCredits());
            userPackageRepository.save(userPackage); // Save updated package

            classSchedule.setAvailableSlots(classSchedule.getAvailableSlots() - 1);
            classScheduleRepository.save(classSchedule);
            return bookingRepository.save(booking);
        } else {
            throw new RuntimeException("No available slots for this class");
        }
    }

    @Transactional
    public void cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        LocalDateTime classStartTime = booking .getClassSchedule().getStartTime();
        if (classStartTime.isAfter(LocalDateTime.now().plusHours(4))) {
            booking.setCancelled(true);
            bookingRepository.save(booking);
            // Refund credits logic here
            refundCredits(booking, booking.getClassSchedule().getRequiredCredits());
        } else {
            throw new RuntimeException("Cancellation period has expired");
        }
    }

    private void refundCredits(Booking booking, int credits) {
        // Logic to add credits back to the user's package


        User user = booking.getUser();
        String country = booking.getClassSchedule().getCountry();

        UserPackage userPackage = userPackageRepository.findByUserAndPkg_Country(user, country)
                .orElseThrow(() -> new RuntimeException("No package available for this country"));

        userPackage.setRemainingCredits(userPackage.getRemainingCredits() + credits);
        userPackageRepository.save(userPackage); // Save updated package
    }

    public List<Booking> getUserBookings(Long userId) {
        return bookingRepository.findByUserId(userId);
    }
}
