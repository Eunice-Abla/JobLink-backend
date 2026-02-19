package com.example.JobLink_backend.controller;

import com.example.JobLink_backend.dto.UserDTO;
import com.example.JobLink_backend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Endpoints for user registration and login synchronization")
public class AuthResource {
    private final UserService userService;

    @PostMapping("/register")
    @Operation(summary = "Register a new user or sync Firebase user", description = "Synchronizes the Firebase user with the local database.")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.registerOrSync(userDTO));
    }

    @PostMapping("/login")
    @Operation(summary = "Login/Sync user", description = "Ensures the user is present in the database after Firebase login.")
    public ResponseEntity<UserDTO> login(@RequestBody UserDTO userDTO) {
        // In Firebase flow, login often just ensures the user is synced in our DB
        return ResponseEntity.ok(userService.registerOrSync(userDTO));
    }
}
