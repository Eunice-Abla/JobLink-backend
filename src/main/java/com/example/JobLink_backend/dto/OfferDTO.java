package com.example.JobLink_backend.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OfferDTO {
    private String id;
    private String title;
    private String company;
    private String location;
    private String description;
    private Integer matchPercentage;
    private String contractType;
    private String salary;
    private String logoUrl;
    private List<String> skillsRequired;
    private LocalDateTime createdAt;
}
