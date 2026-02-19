package com.example.JobLink_backend.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationDTO {
    private String id;
    private String userId;
    private String offerId;
    private String status;
    private LocalDateTime appliedAt;
    private String jobTitle; // For historical context in response
    private String companyName;
}
