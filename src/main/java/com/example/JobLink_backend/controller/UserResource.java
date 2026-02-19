package com.example.JobLink_backend.controller;

import com.example.JobLink_backend.dto.UserDTO;
import com.example.JobLink_backend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "User Profile", description = "Manage user profile and skills")
@SecurityRequirement(name = "bearerAuth")
public class UserResource {
    private final UserService userService;

    @GetMapping("/profile")
    @Operation(summary = "Get user profile", description = "Retrieves the profile of the currently authenticated user.")
    public ResponseEntity<UserDTO> getProfile(@AuthenticationPrincipal String uid) {
        return userService.getUserProfile(uid)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/profile")
    @Operation(summary = "Update user profile", description = "Updates the profile information of the currently authenticated user.")
    public ResponseEntity<UserDTO> updateProfile(@AuthenticationPrincipal String uid, @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.updateProfile(uid, userDTO));
    }

    @PostMapping("/skills")
    @Operation(summary = "Update user skills", description = "Updates the list of skills for the currently authenticated user.")
    public ResponseEntity<UserDTO> updateSkills(@AuthenticationPrincipal String uid, @RequestBody List<String> skills) {
        return ResponseEntity.ok(userService.updateSkills(uid, skills));
    }
}
