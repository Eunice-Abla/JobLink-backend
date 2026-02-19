package com.example.JobLink_backend.controller;

import com.example.JobLink_backend.dto.ApplicationDTO;
import com.example.JobLink_backend.service.ApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
@Tag(name = "Applications", description = "Endpoints for submitting and viewing job applications")
@SecurityRequirement(name = "bearerAuth")
public class ApplicationResource {
    private final ApplicationService applicationService;

    @PostMapping
    @Operation(summary = "Submit application", description = "Submits a job application for the currently authenticated user.")
    public ResponseEntity<ApplicationDTO> submitApplication(
            @AuthenticationPrincipal String uid,
            @RequestBody Map<String, String> request) {
        String offerId = request.get("offerId");
        return ResponseEntity.ok(applicationService.submitApplication(uid, offerId));
    }

    @GetMapping("/my")
    @Operation(summary = "Get my applications", description = "Retrieves the application history for the currently authenticated user.")
    public ResponseEntity<List<ApplicationDTO>> getMyApplications(@AuthenticationPrincipal String uid) {
        return ResponseEntity.ok(applicationService.getMyApplications(uid));
    }
}
