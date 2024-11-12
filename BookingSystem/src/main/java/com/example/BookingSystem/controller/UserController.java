package com.example.BookingSystem.controller;
import com.example.BookingSystem.dto.ChangePasswordDto;
import com.example.BookingSystem.model.User;
import com.example.BookingSystem.service.AuthenticationService;
import com.example.BookingSystem.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users")
@RestController
public class UserController {
    private final UserService userService;
    private final AuthenticationService authenticationService;
    public UserController(UserService userService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @GetMapping("/me")
    public ResponseEntity<User> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        return ResponseEntity.ok(currentUser);
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> allUsers() {
        List <User> users = userService.allUsers();
        return ResponseEntity.ok(users);
    }
    @PostMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDto changePasswordDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser  = (User ) authentication.getPrincipal();
        Long userId = currentUser .getId(); // Assuming User has a getId method
        System.out.println("Heere"+userId);
        authenticationService.changePassword(userId, changePasswordDto.getOldPassword(), changePasswordDto.getNewPassword());
        return ResponseEntity.ok("Password changed successfully");
    }
}
