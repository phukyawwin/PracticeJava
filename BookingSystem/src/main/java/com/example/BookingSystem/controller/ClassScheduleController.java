package com.example.BookingSystem.controller;

import com.example.BookingSystem.model.ClassSchedule;
import com.example.BookingSystem.model.User;
import com.example.BookingSystem.service.ClassScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classSchedules")
public class ClassScheduleController {
    @Autowired
    private ClassScheduleService classScheduleService;

    // Endpoint to create a new class schedule
    @PostMapping
    public ResponseEntity<ClassSchedule> createClassSchedule(@RequestBody ClassSchedule classSchedule) {
        ClassSchedule createdClassSchedule = classScheduleService.createClassSchedule(classSchedule);
        return ResponseEntity.ok(createdClassSchedule);
    }

    // Endpoint to get all class schedules
    @GetMapping
    public ResponseEntity<List<ClassSchedule>> getAllClassSchedules() {
        List<ClassSchedule> classSchedules = classScheduleService .getAllClassSchedules();
        return ResponseEntity.ok(classSchedules);
    }

    @GetMapping("/byUser")
    public List<ClassSchedule> getClassesByCountry() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();

        // Get classes by user's package country
        return classScheduleService.getClassesByUserCountry(currentUser);
    }

    // Endpoint to get a specific class schedule by ID
    @GetMapping("/{classScheduleId}")
    public ResponseEntity<ClassSchedule> getClassScheduleById(@PathVariable Long classScheduleId) {
        ClassSchedule classSchedule = classScheduleService.getClassScheduleById(classScheduleId);
        return ResponseEntity.ok(classSchedule);
    }

    // Endpoint to update a class schedule
    @PutMapping("/{classScheduleId}")
    public ResponseEntity<ClassSchedule> updateClassSchedule(@PathVariable Long classScheduleId, @RequestBody ClassSchedule classScheduleDetails) {
        ClassSchedule updatedClassSchedule = classScheduleService.updateClassSchedule(classScheduleId, classScheduleDetails);
        return ResponseEntity.ok(updatedClassSchedule);
    }

    // Endpoint to delete a class schedule
    @DeleteMapping("/{classScheduleId}")
    public ResponseEntity<String> deleteClassSchedule(@PathVariable Long classScheduleId) {
        try {
            classScheduleService.deleteClassSchedule(classScheduleId);
            return ResponseEntity.ok("Class schedule deleted successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
