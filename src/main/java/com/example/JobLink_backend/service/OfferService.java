package com.example.JobLink_backend.service;

import com.example.JobLink_backend.dto.OfferDTO;
import com.example.JobLink_backend.model.Offer;
import com.example.JobLink_backend.repository.OfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OfferService {
    private final OfferRepository offerRepository;

    public List<OfferDTO> getAllOffers() {
        return offerRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public Optional<OfferDTO> getOfferById(String id) {
        return offerRepository.findById(id).map(this::mapToDTO);
    }

    public List<OfferDTO> searchOffers(String query) {
        return offerRepository.searchOffers(query).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    private OfferDTO mapToDTO(Offer offer) {
        return OfferDTO.builder()
                .id(offer.getId())
                .title(offer.getTitle())
                .company(offer.getCompany())
                .location(offer.getLocation())
                .description(offer.getDescription())
                .matchPercentage(offer.getMatchPercentage())
                .contractType(offer.getContractType())
                .salary(offer.getSalary())
                .logoUrl(offer.getLogoUrl())
                .skillsRequired(offer.getSkillsRequired())
                .createdAt(offer.getCreatedAt())
                .build();
    }
}
