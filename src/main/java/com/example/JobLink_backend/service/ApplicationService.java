package com.example.JobLink_backend.service;

import com.example.JobLink_backend.dto.ApplicationDTO;
import com.example.JobLink_backend.model.Application;
import com.example.JobLink_backend.model.Offer;
import com.example.JobLink_backend.repository.ApplicationRepository;
import com.example.JobLink_backend.repository.OfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final OfferRepository offerRepository;

    @Transactional
    public ApplicationDTO submitApplication(String userId, String offerId) {
        Application application = Application.builder()
                .userId(userId)
                .offerId(offerId)
                .status("En cours")
                .build();
        return mapToDTO(applicationRepository.save(application));
    }

    public List<ApplicationDTO> getMyApplications(String userId) {
        return applicationRepository.findByUserId(userId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private ApplicationDTO mapToDTO(Application application) {
        Offer offer = offerRepository.findById(application.getOfferId()).orElse(null);
        return ApplicationDTO.builder()
                .id(application.getId())
                .userId(application.getUserId())
                .offerId(application.getOfferId())
                .status(application.getStatus())
                .appliedAt(application.getAppliedAt())
                .jobTitle(offer != null ? offer.getTitle() : "Unknown Job")
                .companyName(offer != null ? offer.getCompany() : "Unknown Company")
                .build();
    }
}
