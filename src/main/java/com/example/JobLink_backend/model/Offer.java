package com.example.JobLink_backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "offers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String company;

    private String location;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Integer matchPercentage;

    private String contractType;

    private String salary;

    private String logoUrl;

    @ElementCollection
    @CollectionTable(name = "offer_skills_required", joinColumns = @JoinColumn(name = "offer_id"))
    @Column(name = "skill")
    private List<String> skillsRequired = new ArrayList<>();

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
