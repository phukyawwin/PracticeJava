package com.example.BookingSystem.service;

import com.example.BookingSystem.model.ClassSchedule;
import com.example.BookingSystem.model.User;
import com.example.BookingSystem.model.UserPackage;
import com.example.BookingSystem.model.Package;
import com.example.BookingSystem.repository.ClassScheduleRepository;
import com.example.BookingSystem.repository.UserPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ClassScheduleService {
    @Autowired
    private ClassScheduleRepository classScheduleRepository;
    @Autowired
    private UserPackageRepository userPackageRepository;

    public ClassSchedule createClassSchedule(ClassSchedule classSchedule) {
        return classScheduleRepository.save(classSchedule);
    }

    public List<ClassSchedule> getAllClassSchedules() {
        return classScheduleRepository.findAll();
    }
    public List<ClassSchedule> getClassesByUserCountry(User user) {
        List<UserPackage> userPackages = userPackageRepository.findByUser (user);

        if (userPackages.isEmpty()) {
            throw new RuntimeException("User  has no packages.");
        }

        // Use a Set to collect unique countries
        Set<String> uniqueCountries = new HashSet<>();
        for (UserPackage userPackage : userPackages) {
            Package aPackage = userPackage.getPkg();
            uniqueCountries.add(aPackage.getCountry());
        }

        // Use a Set to collect unique ClassSchedules
        Set<ClassSchedule> uniqueClassSchedules = new HashSet<>();
        for (String country : uniqueCountries) {
            List<ClassSchedule> classSchedules = classScheduleRepository.findByCountry(country);
            uniqueClassSchedules.addAll(classSchedules); // Add all class schedules to the set
        }

        // Convert the Set back to a List before returning
        return new ArrayList<>(uniqueClassSchedules);
    }

    public ClassSchedule getClassScheduleById(Long classScheduleId) {
        return classScheduleRepository.findById(classScheduleId)
                .orElseThrow(() -> new RuntimeException("Class schedule not found"));
    }

    public ClassSchedule updateClassSchedule(Long classScheduleId, ClassSchedule classScheduleDetails) {
        ClassSchedule existingClassSchedule = getClassScheduleById(classScheduleId);
        existingClassSchedule.setName(classScheduleDetails.getName());
        existingClassSchedule.setCountry(classScheduleDetails.getCountry());
        existingClassSchedule.setAvailableSlots(classScheduleDetails.getAvailableSlots());
        existingClassSchedule.setRequiredCredits(classScheduleDetails.getRequiredCredits());
        existingClassSchedule.setStartTime(classScheduleDetails.getStartTime());
        return classScheduleRepository.save(existingClassSchedule);
    }

    public void deleteClassSchedule(Long classScheduleId) {
        ClassSchedule existingClassSchedule = getClassScheduleById(classScheduleId);
        classScheduleRepository.delete(existingClassSchedule);
    }
}
