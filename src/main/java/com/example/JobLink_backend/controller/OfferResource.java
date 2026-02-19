package com.example.JobLink_backend.controller;

import com.example.JobLink_backend.dto.OfferDTO;
import com.example.JobLink_backend.service.OfferService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/offers")
@RequiredArgsConstructor
@Tag(name = "Job Offers", description = "Endpoints for viewing and searching job offers")
public class OfferResource {
    private final OfferService offerService;

    @GetMapping
    @Operation(summary = "Get all job offers", description = "Retrieves a list of all available job offers.")
    public ResponseEntity<List<OfferDTO>> getAllOffers() {
        return ResponseEntity.ok(offerService.getAllOffers());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get offer by ID", description = "Retrieves the details of a specific job offer.")
    public ResponseEntity<OfferDTO> getOfferById(@PathVariable String id) {
        return offerService.getOfferById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    @Operation(summary = "Search job offers", description = "Search for job offers by title, company, or location.")
    public ResponseEntity<List<OfferDTO>> searchOffers(@RequestParam String q) {
        return ResponseEntity.ok(offerService.searchOffers(q));
    }
}
